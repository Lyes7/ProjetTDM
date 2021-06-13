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
import kotlin.collections.ArrayList

class RacineAdapter(var msg: List<Racine>):RecyclerView.Adapter<RacineAdapter.ViewHolder>() , Filterable {


    var countryFilterList = ArrayList<Racine>()

    /*
    arrayOf(
    Verset(0,2,2,"eng",125,15),
    Verset(0,2,2,"eng",125,15)
    ).toList() */

/*
arrayListOf(
        Racine(22,"الم",2),
        Racine(23,"سقي",2)
    )

 */

    private  var dataSet= arrayListOf<Racine>()



    init {
        dataSet.addAll(msg)
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
        holder.title.text = countryFilterList[position].Racine
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
                            if (row.Racine.contains(charSearch)) {
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