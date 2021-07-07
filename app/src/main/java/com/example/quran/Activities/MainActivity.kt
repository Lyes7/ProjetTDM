package com.example.quran.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quran.DAOs.RacineDao
import com.example.quran.DataBase.AppDataBase
import com.example.quran.Fragment.HistoricFragment
import com.example.quran.R
import com.example.quran.Fragment.RvRacineFragment
import com.example.quran.Models.Racine
import com.example.quran.DataBase.ServiceRoom
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        history.setOnClickListener {

            val histRacineFragment =HistoricFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,  histRacineFragment)
                addToBackStack(null)
                commit()
            }
        }


        val racines=ServiceRoom.database.racineDao()?.getRacines()


        val racineFragment = RvRacineFragment(racines!!)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,  racineFragment)
            commit()
        }

    }
}