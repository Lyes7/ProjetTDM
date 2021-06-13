package com.example.quran

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import com.example.quran.Models.Racine
class RvAyaFragment(private val racine: Racine) : Fragment(R.layout.fragment_rv_aya) {

    //var editText = view?.findViewById<EditText>(R.id.edited_text)



    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AyahRecyclerAdapter.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  editText?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })*/

        layoutManager = LinearLayoutManager(requireContext())



        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_aya)
        recyclerView?.layoutManager = this.layoutManager


        adapter = AyahRecyclerAdapter(racine)

        recyclerView?.adapter = this.adapter
        //var a = arguments?.get("msg")

    }

    private fun filter(text: String) {

    }


}