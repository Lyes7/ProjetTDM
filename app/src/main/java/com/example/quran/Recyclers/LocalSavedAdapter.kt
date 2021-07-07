package com.example.quran.Recyclers

import android.content.Context
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
import com.example.quran.Fragment.DetailVerset
import com.example.quran.Fragment.HistoricFragment
import com.example.quran.Fragment.RvAyaFragment
import com.example.quran.Fragment.SavedRvFragment
import com.example.quran.Models.HistoricRacine
import com.example.quran.Models.Racine
import com.example.quran.Models.SavedVerset
import com.example.quran.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class LocalSavedAdapter(context : Context):RecyclerView.Adapter<LocalSavedAdapter.ViewHolder>() {


    private var msg = ServiceRoom.database.savedVersetDao()?.getSavedVersets()


    private  var dataSet= arrayListOf<SavedVerset>()



    init {
        dataSet.addAll(msg)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.SuraName)
        var ayah: TextView = itemView.findViewById(R.id.AyaTv)
        var note: TextView = itemView.findViewById(R.id.note)
        var deletBtn: TextView = itemView.findViewById(R.id.deletBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.saved_aya_card, parent, false)
        return ViewHolder(viewObj)
    }


    override fun getItemCount(): Int {
        return dataSet?.size!!
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var targetAya = ServiceRoom.database.versetDao()?.getVersetByNumId(dataSet!![position].IdAya)

        holder.title.text = "سورة "+ServiceRoom.database.surahDao()?.getSurahById(targetAya.IdSourat)?.get(0)?.NomSourat
        holder.ayah.text = targetAya.Text_AR
        holder.note.text = dataSet!![position].note
        holder.deletBtn.setOnClickListener {
            ServiceRoom.database.savedVersetDao()?.deleteSavedVersetByNote(dataSet!![position].IdAya,dataSet!![position].note)
            val savedRvAyaFragment = SavedRvFragment()

            val activity = it!!.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.savedFragment,  savedRvAyaFragment)
                commit()
            }
        }
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity

                val detailFragment = DetailVerset(targetAya)

                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.savedFragment,  detailFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        })
    }





}