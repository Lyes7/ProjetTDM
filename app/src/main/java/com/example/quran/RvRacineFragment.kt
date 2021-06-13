package com.example.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Models.Racine
import kotlinx.android.synthetic.main.fragment_rv_racine.*

class RvRacineFragment : Fragment(R.layout.fragment_rv_racine){


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RacineAdapter.ViewHolder>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())


        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_racine)
        recyclerView?.layoutManager = this.layoutManager

        adapter = RacineAdapter()

        recyclerView?.adapter = this.adapter

    }



}