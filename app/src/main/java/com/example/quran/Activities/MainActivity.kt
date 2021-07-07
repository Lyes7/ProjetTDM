package com.example.quran.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quran.DAOs.RacineDao
import com.example.quran.DataBase.AppDataBase
import com.example.quran.R
import com.example.quran.Fragment.RvRacineFragment
<<<<<<< HEAD
import com.example.quran.Models.Racine
import com.example.quran.DataBase.ServiceRoom
=======
>>>>>>> parent of 01b7046... test commit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

<<<<<<< HEAD
        val racines=ServiceRoom.database.racineDao()?.getRacines()
        
        val racineFragment = RvRacineFragment(racines!!)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,  racineFragment)
            commit()
        }
=======
        Observable.fromCallable {
            db = AppDataBase.getAppDataBase(context = this)
            racineDao = db?.racineDao()



            with(racineDao) {
               // this?.insertRacine(racine1)
                //this?.insertRacine(racine2)

            }
            db?.racineDao()?.getRacines()
        }.doOnNext { list ->
            var finalString = ""
            list?.map { finalString += it.Racine + " - " }
            val racineFragment = RvRacineFragment(list!!)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,  racineFragment)
                //addToBackStack(null)
                commit()
            }


        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()






        //val ayaFragment = RvAyaFragment()



>>>>>>> parent of 01b7046... test commit
    }
}