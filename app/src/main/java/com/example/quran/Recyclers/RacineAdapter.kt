package com.example.quran.Recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import com.example.quran.DataBase.ServiceRoom
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.quran.DataBase.AppDataBase
import com.example.quran.Fragment.RvAyaFragment
import com.example.quran.Models.HistoricRacine
import com.example.quran.Models.Racine
import com.example.quran.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class RacineAdapter(var msg: List<Racine>):RecyclerView.Adapter<RacineAdapter.ViewHolder>() , Filterable {


    var racinesFilterList = ArrayList<Racine>()


    private  var dataSet= arrayListOf<Racine>()



    init {
        dataSet.addAll(msg)
        racinesFilterList = dataSet
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.idRacine)
        var title: TextView = itemView.findViewById(R.id.racineTv)
        var len: TextView = itemView.findViewById(R.id.lenRacine)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.racine_card, parent, false)
        return ViewHolder(viewObj)
    }


    override fun getItemCount(): Int {
        return racinesFilterList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = racinesFilterList[position].idRacine.toString()
        holder.title.text = racinesFilterList[position].Racine
        holder.len.text = racinesFilterList[position].NBLettre.toString()

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity

               ServiceRoom.database.historicRacineDao().insertHistRacine(HistoricRacine(racinesFilterList[position].idRacine))

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