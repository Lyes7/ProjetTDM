import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.quran.Activities.SignInActivity
import com.example.quran.DataBase.ServiceRoom
import com.example.quran.Models.AyaFire
import com.example.quran.Models.SavedVerset
import com.example.quran.Models.Verset
import com.example.quran.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_pop_up__note.*

class MyCustomDialog(val verset: Verset): DialogFragment() {

    companion object{
        private const val RC_SIGN_IN=120
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignIn: GoogleSignInClient

    val db = FirebaseFirestore.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.fragment_pop_up__note, container, false)
        FirebaseApp.initializeApp(requireContext())
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignIn = GoogleSignIn.getClient(requireContext(), gso)

        mAuth = FirebaseAuth.getInstance()


        var user = mAuth.currentUser


        print("ddddddddddddddd dfqsdf sdfQDFQDF"+user)



        addOffline.setOnClickListener {
            val saveVerset = SavedVerset(0,verset.IdAya,noteTextArea.text.toString())
            ServiceRoom.database.savedVersetDao()?.insertSavedVerset(saveVerset)
            this.dismiss()
            Toast.makeText(requireContext(), "تم إضافة الآية إلى المفضلة بنجاح", Toast.LENGTH_LONG).show()
        }


        addOnline.setOnClickListener() {

            if (user != null) {
                val text = noteTextArea.text
                Toast.makeText(requireContext(), user.uid, Toast.LENGTH_LONG).show()
                db.collection("fav").add(AyaFire(verset.IdAya,text.toString(),user.uid)).addOnCompleteListener { task: Task<DocumentReference> ->
                    if (task.isSuccessful) {
                        println("success !!!!")
                        //*****
                        db.collection("fav").whereEqualTo("userId", user.uid).get().addOnCompleteListener { task: Task<QuerySnapshot> ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (!document!!.isEmpty) {
                                    val users = document.toObjects(AyaFire::class.java)

                                    users.forEach {
                                        Toast.makeText(requireContext(), it.ayaIdFire, Toast.LENGTH_SHORT).show()

                                    }
                                } else {
                                    println("No such document")
                                }
                            } else {
                                println(task.exception)
                            }
                        }


                        //********

                    } else {
                        println(task.exception)
                    }
                }
                // addFav()

            } else {

                signIn()


                /*val signInIntent =Intent(activity?.applicationContext, SignInActivity::class.java)
                //Intent(this, SingInActivity::class.java)

             startActivity(signInIntent)*/

            }
          // this.dismiss()
        }


    }

    private fun signIn() {
        val signInIntent = googleSignIn.signInIntent
        startActivityForResult(signInIntent, MyCustomDialog.RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == MyCustomDialog.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception=task.exception
            if(task.isSuccessful)
            {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    // Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    print( "COMPTE :" + account.id.toString())

                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    // Log.w("SignInActivity", "Google sign in failed", e)
                    print("SignInActivity "+ "GOOGLE MAFIHAACH"+ e.toString())
                }}else{
                //Log.w("SignInActivity", exception.toString())
                print("SignInActivity "+ exception.toString())

            }

        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    print("SignInActivity "+ "NAJA7NAA")

                } else {
                    // If sign in fails, display a message to the user.
                    // Log.w("SignInActivity", "signInWithCredential:failure", task.exception)
                    print("SignInActivity "+ "faaaaailllllerrr "+ task.exception.toString())

                }
            }
    }

}