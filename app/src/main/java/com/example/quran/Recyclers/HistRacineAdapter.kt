package com.example.quran.Recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.DataBase.ServiceRoom
import com.example.quran.Fragment.HistoricFragment
import com.example.quran.Fragment.RvAyaFragment
import com.example.quran.Models.Racine
import com.example.quran.R


class HistRacineAdapter(var msg: List<Racine>):RecyclerView.Adapter<HistRacineAdapter.ViewHolder>() , Filterable {


    var racinesFilterList = ArrayList<Racine>()


    private  var dataSet= arrayListOf<Racine>()



    init {
        dataSet.addAll(msg)
        racinesFilterList = dataSet
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.idRacine)
        var title: TextView = itemView.findViewById(R.id.racineTv)
        var deleteRacine: TextView = itemView.findViewById(R.id.delete_racine)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.hist_racine_card, parent, false)
        return ViewHolder(viewObj)

    }


    override fun getItemCount(): Int {
        return racinesFilterList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = racinesFilterList[position].idRacine.toString()
        holder.title.text = racinesFilterList[position].Racine
        holder.deleteRacine.setOnClickListener {
            ServiceRoom.database.historicRacineDao().deleteHistRacine(racinesFilterList[position].idRacine)
            val histRacineFragment =HistoricFragment()

            val activity = it!!.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,  histRacineFragment)

                commit()
            }
        }

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {


                val activity = v!!.context as AppCompatActivity


                val ayaFragment = RvAyaFragment(racinesFilterList[position])

               activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  ayaFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }
        )
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    racinesFilterList = dataSet
                } else {
                    val resultList = ArrayList<Racine>()
                    for (row in dataSet) {
                            if (row.Racine.contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                    racinesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = racinesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                racinesFilterList = results?.values as ArrayList<Racine>
                notifyDataSetChanged()
            }

        }
    }



}