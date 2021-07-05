package com.example.quran.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Recyclers.AyahRecyclerAdapter
import com.example.quran.Models.Racine
import com.example.quran.R

class RvAyaFragment(private val racine: Racine) : Fragment(R.layout.fragment_rv_aya) {


        private var layoutManager: RecyclerView.LayoutManager? = null
        private var adapter: RecyclerView.Adapter<AyahRecyclerAdapter.ViewHolder>? = null

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            layoutManager = LinearLayoutManager(requireContext())
            var listTitle = view.findViewById<TextView>(R.id.listAyaTitle)
            listTitle.text = "الآيات التي تحتوي على الجذر: " + racine.Racine


            val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_aya)
            recyclerView?.layoutManager = this.layoutManager

            adapter = AyahRecyclerAdapter(this.requireContext(),racine)

            recyclerView?.adapter = this.adapter

        }

        private fun filter(text: String) {

        }


    }

