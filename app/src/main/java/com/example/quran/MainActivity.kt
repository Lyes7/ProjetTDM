package com.example.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quran.Models.Racine

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        val racineFragment = RvRacineFragment()
        //val ayaFragment = RvAyaFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,  racineFragment)
            //addToBackStack(null)
            commit()
        }

    }


}