package com.example.quran.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.R
import com.example.quran.Recyclers.LocalSavedAdapter


class SavedRvFragment : Fragment(R.layout.fragment_saved_rv) {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<LocalSavedAdapter.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(requireContext())

        val recyclerView = view?.findViewById<RecyclerView>(R.id.saved_rv_aya)
        recyclerView?.layoutManager = this.layoutManager

        adapter = LocalSavedAdapter(this.requireContext())

        recyclerView?.adapter = this.adapter


    }

}