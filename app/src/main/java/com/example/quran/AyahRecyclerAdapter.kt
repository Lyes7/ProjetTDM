package com.example.quran

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.DataBase.AppDataBase
import com.example.quran.Models.Racine
import com.example.quran.Models.Verset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch





class AyahRecyclerAdapter(context : Context, val racine: Racine): RecyclerView.Adapter<AyahRecyclerAdapter.ViewHolder>(){

    private var db: AppDataBase? = AppDataBase.getAppDataBase(context)
    private var dataSet: List<Verset>? =  db?.versetDao()?.getVersetsByRacine(racine.idRacine)




    //private  var dataSet = racine.listVerset


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.SuraName)
        var ayah: TextView = itemView.findViewById(R.id.AyaTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahRecyclerAdapter.ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.aya_card, parent, false)
        return ViewHolder(viewObj)
    }

    override fun getItemCount(): Int {

        return dataSet?.size!!

    }

    override fun onBindViewHolder(holder: AyahRecyclerAdapter.ViewHolder, position: Int) {

        holder.title.text = "test"
        holder.ayah.text = dataSet!![position].Text_AR
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity

                val detailFragment = DetailVerset(dataSet!![position])


                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  detailFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        })

    }





}
