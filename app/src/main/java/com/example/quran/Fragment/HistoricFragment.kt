package com.example.quran.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.DataBase.ServiceRoom
import com.example.quran.R
import com.example.quran.Recyclers.HistRacineAdapter
import kotlinx.android.synthetic.main.fragment_rv_racine.*

class HistoricFragment:Fragment(R.layout.fragment_rv_hist_racine){

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HistRacineAdapter.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())

        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_hist_racine)
        val msg = ServiceRoom.database.historicRacineDao()?.getHistRacines()

        recyclerView?.layoutManager = this.layoutManager

        adapter = HistRacineAdapter(msg)


        recyclerView?.adapter = this.adapter

        et_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (adapter as HistRacineAdapter).filter.filter(newText)
                return false
            }

        })

    }







}