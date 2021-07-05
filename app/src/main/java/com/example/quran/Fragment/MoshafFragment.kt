package com.example.quran.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import com.example.quran.R
import com.squareup.picasso.Picasso


class MoshafFragment(val page:Int) : Fragment(R.layout.fragment_moshaf) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://quran-images-api.herokuapp.com/show/page/"+page.toString()
        val img = view.findViewById<ImageView>(R.id.moshaf)
        Picasso.get().load(url).into(img)


    }
}