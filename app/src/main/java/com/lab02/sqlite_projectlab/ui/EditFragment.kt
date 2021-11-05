package com.lab02.sqlite_projectlab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lab02.sqlite_projectlab.R
import com.lab02.sqlite_projectlab.db.DB_Helper
import com.lab02.sqlite_projectlab.db.Hospital
import kotlinx.android.synthetic.main.fragment_edit.view.*

class EditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_edit, container, false)

        view.buttonEdit.setOnClickListener{
            val id = view.editTextId.text.toString().toInt()
            val name = view.editTextHospitalName.text.toString()
            val address = view.editTextAddress.text.toString()
            val description = view.editTextDescription.text.toString()
            val latitude = view.editTextLatitude.text.toString().toFloat()
            val longitude = view.editTextLongitude.text.toString().toFloat()
            val hospital = Hospital(name, address, description, latitude, longitude)
            val db          = DB_Helper(container!!.context)
            db.updateData(id, hospital)
            returnToList()
        }

        view.buttonCancel.setOnClickListener{
            returnToList()
        }
        return view
    }

    fun returnToList(){
        var fr = getFragmentManager()?.beginTransaction()
        fr?.replace(R.id.fragment_container, ListViewFragment())
        fr?.commit()
    }

}