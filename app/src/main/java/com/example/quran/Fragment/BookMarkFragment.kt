package com.example.quran.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quran.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_book_mark.*


class BookMarkFragment : Fragment(R.layout.fragment_book_mark) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val SavedRvFragment = SavedRvFragment()
        val activity = view.context as AppCompatActivity

        activity.supportFragmentManager.beginTransaction().apply {
            replace(R.id.savedFragment,  SavedRvFragment)
            commit()
        }

        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Toast.makeText(activity, "Tab " + tab.position, Toast.LENGTH_LONG).show()
                if(tab.position == 0){
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.savedFragment,  SavedRvFragment)
                        commit()
                    }

                }else{
                    val frag = HistoricFragment()
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.savedFragment,  frag)
                        commit()
                    }

                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }
}