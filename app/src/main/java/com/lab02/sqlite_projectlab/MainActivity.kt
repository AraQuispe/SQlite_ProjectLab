package com.lab02.sqlite_projectlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lab02.sqlite_projectlab.adaptadores.AdapterHospital
import com.lab02.sqlite_projectlab.db.DB_Helper
import com.lab02.sqlite_projectlab.ui.CreateFragment
import com.lab02.sqlite_projectlab.ui.HospitalViewFragment

class MainActivity : AppCompatActivity(){
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var adapter: AdapterHospital
    val listaData:List<DB_Helper> = ArrayList()


    private val createFragment = CreateFragment()
    private val listFragment =  HospitalViewFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(createFragment)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.create-> replaceFragment(createFragment)
                R.id.viewList -> replaceFragment(listFragment)
            }
            true
            }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}