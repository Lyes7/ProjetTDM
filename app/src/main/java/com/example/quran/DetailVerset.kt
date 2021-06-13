package com.example.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quran.Models.Verset

class DetailVerset(val varset: Verset) : Fragment(R.layout.fragment_detail_verset) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detail_textView = view.findViewById<TextView>(R.id.DetailTV)

        detail_textView.text = varset.TextVersetArabe

    }

}