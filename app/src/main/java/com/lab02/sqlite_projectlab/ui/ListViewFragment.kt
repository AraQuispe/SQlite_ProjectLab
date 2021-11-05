package com.lab02.sqlite_projectlab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.lab02.sqlite_projectlab.MainActivity
import com.lab02.sqlite_projectlab.R
import com.lab02.sqlite_projectlab.db.DB_Helper
import kotlinx.android.synthetic.main.fragment_list_view.view.*

class ListViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_view, container, false)

        val db = DB_Helper(container!!.context)
        val data = db.readData()
        view.test.text = ""
        for(i in 0 until data.size){
            view.test.append(data[i].toString() + "\n")
        }

        view.floatingActionButton.setOnClickListener{
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.fragment_container, EditFragment())
            fr?.commit()
        }

        return view
    }

}