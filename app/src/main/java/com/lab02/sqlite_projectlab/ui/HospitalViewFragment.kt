package com.lab02.sqlite_projectlab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lab02.sqlite_projectlab.R
import com.lab02.sqlite_projectlab.adaptadores.AdapterHospital
import com.lab02.sqlite_projectlab.db.DB_Helper


class HospitalViewFragment : Fragment() {

    //private var _binding:ListHospital?=null
   // private val binding get() = _binding!!
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : AdapterHospital = AdapterHospital()





    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // _binding = ListHospital.inflate(inflater)
      //  var view =  binding.root
       // return view

        val view: View = inflater.inflate(R.layout.fragment_hospital_view, container, false)
        val db = DB_Helper(container!!.context)
        mRecyclerView = view.findViewById(R.id.recyclerViewHospitals) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(container.context)
        mAdapter.AdapterHospital(db.readData(), container.context)
        mRecyclerView.adapter = mAdapter
     //   val db = DB_Helper(container!!.context)
      //  val data = db.readData()
      //  view.test.text = ""
      //  for(i in 0 until data.size){
       //     view.test.append(data[i].toString() + "\n")
       // }

       // view.floatingActionButton.setOnClickListener{
       //     var fr = getFragmentManager()?.beginTransaction()
       //     fr?.replace(R.id.fragment_container, EditFragment())
        //    fr?.commit()
       // }

    return view
    }

}