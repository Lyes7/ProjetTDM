package com.example.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Models.Racine
class RvAyaFragment(private val racine: Racine) : Fragment(R.layout.fragment_rv_aya) {


        private var layoutManager: RecyclerView.LayoutManager? = null
        private var adapter: RecyclerView.Adapter<AyahRecyclerAdapter.ViewHolder>? = null

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            layoutManager = LinearLayoutManager(requireContext())


            val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_aya)
            recyclerView?.layoutManager = this.layoutManager

            adapter = AyahRecyclerAdapter(racine)

            recyclerView?.adapter = this.adapter

        }

        private fun filter(text: String) {

        }


    }

