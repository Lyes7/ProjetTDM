package com.example.quran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Models.Racine
import com.example.quran.Models.Verset
import java.util.*
import kotlin.collections.ArrayList

class RacineAdapter:RecyclerView.Adapter<RacineAdapter.ViewHolder>() , Filterable {

    var countryFilterList = ArrayList<Racine>()


    private  var dataSet = arrayListOf(
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
                Verset(0,"ejjjjjjjjjjjjjjjj","eng",125,15,23,15),
                Verset(5,"hghgh","eygqng",125,15,23,15)
            ).toList()
        )
    )

    init {
        countryFilterList = dataSet
    }


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
        return countryFilterList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = countryFilterList[position].racine
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity

                val ayaFragment = RvAyaFragment(countryFilterList[position])
              

                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  ayaFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        })
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = dataSet
                } else {
                    val resultList = ArrayList<Racine>()
                    for (row in dataSet) {
                            if (row.racine.contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Racine>
                notifyDataSetChanged()
            }

        }
    }



}