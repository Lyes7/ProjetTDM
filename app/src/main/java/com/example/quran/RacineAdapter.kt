package com.example.quran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Models.Racine
import com.example.quran.Models.Verset

class RacineAdapter:RecyclerView.Adapter<RacineAdapter.ViewHolder>() {
    private  var dataSet = arrayOf(
        Racine(22,
            "الم",
            arrayOf(
                Verset(0,"ee","eng",125,15,23,15),
                Verset(5,"hghgh","eygqng",125,15,23,15)
            ).toList()
        ),
        Racine(23,
            "سقي",
            arrayOf(
                Verset(0,"ee","eng",125,15,23,15),
                Verset(5,"hghgh","eygqng",125,15,23,15)
            ).toList()
        )
    )
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //var id: TextView = itemView.findViewById(R.id.SuraName)
        var title: TextView = itemView.findViewById(R.id.Abc)
        //var racine: TextView = itemView.findViewById(R.id.RacineLen)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacineAdapter.ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.racine_card, parent, false)
        return RacineAdapter.ViewHolder(viewObj)
    }




    override fun getItemCount(): Int {
        return dataSet.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSet[position].racine
    }

}