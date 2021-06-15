package com.example.quran

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quran.ApiControllers.Request.AyaService
import com.example.quran.ApiControllers.Request.TafsirService
import com.example.quran.ApiControllers.Response.AyaIndexResponse
import com.example.quran.ApiControllers.Response.AyaResponse
import com.example.quran.ApiControllers.Response.TafsirResponse
import com.example.quran.Models.Mofasir
import com.example.quran.Models.Verset
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DetailVerset(val verset: Verset) : Fragment(R.layout.fragment_detail_verset){




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mushafBtn = view.findViewById<Button>(R.id.button)



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

    val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,mofasirin)



    val ayaEng = view?.findViewById<TextView>(R.id.ayaEn)

  // val adapterView = view.findViewById<AutoCompleteTextView>(R.id.autoComplete)
    //adapterView.setAdapter(arrayAdapter)



    val url = "https://salamquran.com/en/api/v6/"
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


    val retrofitIndex = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ayaApi = retrofitIndex.create(AyaService::class.java)
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

            val retrofit = Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val ayaApi = retrofit.create(AyaService::class.java)
            val response = ayaApi.getAya(resIndex?.result!!.index)
            response.enqueue(object : Callback<AyaResponse>{
                override fun onResponse(call: Call<AyaResponse>, response: Response<AyaResponse>) {
                    val res = response.body()
                    ayaEng.text = res!!.result?.translate?.text

                }
                //failer of AyaResponse
                override fun onFailure(call: Call<AyaResponse>, t: Throwable) {

                }

            })
        }

        override fun onFailure(call: Call<AyaIndexResponse>, t: Throwable) {



        }

    })









    }



}