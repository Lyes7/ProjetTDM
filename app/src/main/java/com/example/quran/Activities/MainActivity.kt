package com.example.quran.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quran.DAOs.RacineDao
import com.example.quran.DataBase.AppDataBase
import com.example.quran.Fragment.HistoricFragment
import com.example.quran.R
import com.example.quran.Fragment.RvRacineFragment
import com.example.quran.Models.Racine
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var db: AppDataBase? = null
    private var racineDao: RacineDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

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


/*
        Observable.fromCallable {
            db = AppDataBase.getAppDataBase(context = this)
            histRacineDao = db?.historicRacineDao()



            with(histRacineDao) {
                // this?.insertRacine(racine1)
                //this?.insertRacine(racine2)

            }
            db?.historicRacineDao()?.getHistRacines()
        }.doOnNext { list ->
            var finalString = ""
            list?.map { finalString += it.Racine + " - " }
            val historicFragment = HistoricFragment(list!!)
            history.setOnClickListener {

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  historicFragment)
                    commit()
                }
            }


        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()  */








        //val ayaFragment = RvAyaFragment()



    }


}