package com.example.quran.Fragment

import MyCustomDialog
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quran.Activities.SignInActivity

import com.example.quran.ApiControllers.Request.AyaService
import com.example.quran.ApiControllers.Request.TafsirService
import com.example.quran.ApiControllers.Response.AyaIndexResponse
import com.example.quran.ApiControllers.Response.AyaResponse
import com.example.quran.ApiControllers.Response.TafsirResponse
import com.example.quran.Models.Mofasir
import com.example.quran.Models.Verset
import com.example.quran.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activitysignin.*
import kotlinx.android.synthetic.main.fragment_detail_verset.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DetailVerset(val verset: Verset) : Fragment(R.layout.fragment_detail_verset){


    private val mediaPlayer =  MediaPlayer();


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mushafBtn = view.findViewById<Button>(R.id.button)
        val addFavoris = view.findViewById<TextView>(R.id.favori)

        nbrMots.text = verset.nbMots.toString()

       
        addFavoris.setOnClickListener {
            MyCustomDialog(verset).show(requireActivity()!!.supportFragmentManager, "MyCustomFragment")
        }

        var played = false
        var preapred = false
        audioBtn.setOnClickListener {
            if(!preapred){
                val uri =  "https://cdn.islamic.network/quran/audio/128/ar.alafasy/"+verset.ayaIndex.toString()+".mp3"
                prepareAudio(uri)
                preapred = true
            }
            if (played) {
                mediaPlayer.pause()
                audioBtn.setImageResource(R.drawable.ic_play)
                played = false

            }else{
                mediaPlayer.start()
                audioBtn.setImageResource(R.drawable.ic_pause)
                played = true

            }


        }




    val detail_textView = view.findViewById<TextView>(R.id.ayaAr)
    detail_textView.text = verset.Text_AR


    val mofasirin = arrayListOf<Mofasir>(
        Mofasir(1, "التفسير الميسر"),
        Mofasir(2, "تفسير الجلالين"),
        Mofasir(3, "تفسير السعدي"),
        Mofasir(4, "تفسير ابن كثير"),
        Mofasir(5, "تفسير الوسيط لطنطاوي"),
        Mofasir(6, "تفسير البغوي"),
        Mofasir(7, "تفسير القرطبي"),
        Mofasir(8, "تفسير الطبري"),
        Mofasir(9, "Arberry"),
        Mofasir(10, "Yusuf Ali"),
        Mofasir(11, "Keyzer"),
        Mofasir(12, "Leemhuis"),
        Mofasir(13, "Siregar")
        )
    val test = view.findViewById<TextView>(R.id.tafsir)

    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item,mofasirin)

    val ayaEng = view?.findViewById<TextView>(R.id.ayaEn)

  // val adapterView = view.findViewById<AutoCompleteTextView>(R.id.autoComplete)
    //adapterView.setAdapter(arrayAdapter)

    val url = "http://salamquran.com/en/api/v6/"

    val retrofit = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val ayaApiTranslate = retrofit.create(AyaService::class.java)
    val response = ayaApiTranslate.getAya(verset.ayaIndex)
    response.enqueue(object : Callback<AyaResponse>{
        override fun onResponse(call: Call<AyaResponse>, response: Response<AyaResponse>) {
            val res = response.body()
            ayaEng.text = res?.result?.translate?.text

        }
        //failer of AyaResponse
        override fun onFailure(call: Call<AyaResponse>, t: Throwable) {

        }

    })


    val tafsirUrl = "http://api.quran-tafseer.com/"
    val retrofitTafsir = Retrofit.Builder().baseUrl(tafsirUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val tafsirApi = retrofitTafsir.create(TafsirService::class.java)

        //get mofassir from drop list
        val dropList = view.findViewById<Spinner>(R.id.ListMofasirin)
        val tafsirSection = view.findViewById<TextView>(R.id.tafsirSection)

        dropList.adapter = arrayAdapter
        dropList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedObject = dropList.selectedItem as Mofasir

                val responseTafsir = tafsirApi.getTafsirByMofasirId(selectedObject.id,verset.IdSourat,verset.NumAya)

                responseTafsir.enqueue(object: Callback<TafsirResponse> {
                    override fun onResponse(call: Call<TafsirResponse>,responseTafsir: Response<TafsirResponse>) {
                        tafsirSection.text = responseTafsir.body()?.text

                    }

                    override fun onFailure(call: Call<TafsirResponse>, t: Throwable) {

                    }
                } )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
 }



    val ayaApi = retrofit.create(AyaService::class.java)
    val responseIndex = ayaApi.getAyaIndex(verset.IdSourat,verset.NumAya,1)

    responseIndex.enqueue(object:Callback<AyaIndexResponse>{
        override fun onResponse(call: Call<AyaIndexResponse>,responseIndex: Response<AyaIndexResponse>) {
            val resIndex = responseIndex.body()

            //handel moshaf link
            mushafBtn.setOnClickListener {
                val activity = it.context as AppCompatActivity

                val moshafFragment = MoshafFragment(resIndex?.result!!.page)


                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  moshafFragment)
                    addToBackStack(null)
                    commit()
                }
            }


        }

        override fun onFailure(call: Call<AyaIndexResponse>, t: Throwable) {



        }



    })



    }

    private fun addFav() {

    }

    private fun prepareAudio(audioUrl: String) {


        // initializing media player


        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
        } catch ( e : IOException) {
            e.printStackTrace();
        }
        // below line is use to display a toast message.
        Toast.makeText(requireContext(),"تم تشغيل القراءة...", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.stop()
    }
}





