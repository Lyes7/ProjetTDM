package com.example.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.quran.ApiControllers.Request.AyaService
import com.example.quran.ApiControllers.Response.AyaResponse
import com.squareup.picasso.Picasso
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoshafFragment(val page:Int) : Fragment(R.layout.fragment_moshaf) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://quran-images-api.herokuapp.com/show/page/"+page.toString()
        val img = view.findViewById<ImageView>(R.id.moshaf)
        Picasso.get().load(url).into(img)


    }
}