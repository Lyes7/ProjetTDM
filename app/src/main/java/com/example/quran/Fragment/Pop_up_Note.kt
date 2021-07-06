import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.quran.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_pop_up__note.*

class MyCustomDialog: DialogFragment() {

    companion object{
        private const val RC_SIGN_IN=120
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignIn: GoogleSignInClient




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.fragment_pop_up__note, container, false)
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

        mAuth= FirebaseAuth.getInstance()
        val user = mAuth.currentUser


        addOnline.setOnClickListener() {

            if (user != null) {

                // addFav()

            } else {

                signIn()


                /*val signInIntent =Intent(activity?.applicationContext, SignInActivity::class.java)
                //Intent(this, SingInActivity::class.java)

             startActivity(signInIntent)*/

            }
        }


    }

    private fun signIn() {
        val signInIntent = googleSignIn.signInIntent
        startActivityForResult(signInIntent, MyCustomDialog.RC_SIGN_IN)
    }

}