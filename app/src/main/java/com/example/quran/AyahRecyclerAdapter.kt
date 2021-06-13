package com.example.quran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.Models.Verset


class AyahRecyclerAdapter: RecyclerView.Adapter<AyahRecyclerAdapter.ViewHolder>() {

    private  var dataSet = arrayOf(
        Verset(5,"هُوَ الَّذِي يُسَيِّرُكُمْ فِي الْبَرِّ وَالْبَحْرِ حَتَّى إِذَا كُنْتُمْ فِي الْفُلْكِ وَجَرَيْنَ بِهِمْ بِرِيحٍ طَيِّبَةٍ وَفَرِحُوا بِهَا جَاءَتْهَا رِيحٌ عَاصِفٌ وَجَاءَهُمُ الْمَوْجُ مِنْ كُلِّ مَكَانٍ وَظَنُّوا أَنَّهُمْ أُحِيطَ بِهِمْ دَعَوُا اللَّهَ مُخْلِصِينَ لَهُ الدِّينَ لَئِنْ أَنْجَيْتَنَا مِنْ هَذِهِ لَنَكُونَنَّ مِنَ الشَّاكِرِينَ","eygqng",125,15,23,15)
    ).toList()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.SuraName)
        var ayah: TextView = itemView.findViewById(R.id.AyaTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahRecyclerAdapter.ViewHolder {
        var viewObj = LayoutInflater.from(parent.context).inflate(R.layout.aya_card, parent, false)
        return ViewHolder(viewObj)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: AyahRecyclerAdapter.ViewHolder, position: Int) {
        holder.title.text = "سورة البقرة"
        holder.ayah.text = dataSet[position].TextVersetArabe

    }


}
