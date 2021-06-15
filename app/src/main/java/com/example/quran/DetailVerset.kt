package com.example.quran

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.quran.Models.Verset
import kotlinx.android.synthetic.main.fragment_detail_verset.*

class DetailVerset(val varset: Verset) : Fragment(R.layout.fragment_detail_verset){



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detail_textView = view.findViewById<TextView>(R.id.ayaAr)
        detail_textView.text = varset.Text_AR


    val a = arrayOf("mohamed","lyes","lamin")
    val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,a)

    val tt = view?.findViewById<TextView>(R.id.kari2)

    val adapterView = view.findViewById<AutoCompleteTextView>(R.id.autoComplete)
    adapterView.setAdapter(arrayAdapter)

    }



}