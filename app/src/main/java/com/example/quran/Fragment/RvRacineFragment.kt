package com.example.quran.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Models.Racine
import com.example.quran.R
import com.example.quran.Recyclers.RacineAdapter
import kotlinx.android.synthetic.main.fragment_rv_racine.*

class RvRacineFragment(var msg: List<Racine>) : Fragment(R.layout.fragment_rv_racine){


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RacineAdapter.ViewHolder>? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        layoutManager = LinearLayoutManager(requireContext())


        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_racine)


        recyclerView?.layoutManager = this.layoutManager




        adapter = RacineAdapter(msg)



        recyclerView?.adapter = this.adapter

        et_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (adapter as RacineAdapter).filter.filter(newText)
                return false
            }

        })

    }







}