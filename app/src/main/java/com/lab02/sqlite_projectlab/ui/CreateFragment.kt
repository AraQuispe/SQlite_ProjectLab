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
import kotlinx.android.synthetic.main.fragment_create.view.*

class CreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_create, container, false)

        view.buttonAdd.setOnClickListener() {
            val name        = view.editTextHospitalName.text.toString()
            val address     = view.editTextAddress.text.toString()
            val description = view.editTextDescription.text.toString()
            val latitude    = view.editTextLatitude.text.toString().toFloat()
            val longitude   = view.editTextLongitude.text.toString().toFloat()
            val hospital    = Hospital(name, address, description, latitude, longitude)
            val db          = DB_Helper(container!!.context)
            db.insertDB(hospital)
            view.editTextHospitalName.text?.clear()
            view.editTextAddress.text?.clear()
            view.editTextDescription.text?.clear()
            view.editTextLatitude.text?.clear()
            view.editTextLongitude.text?.clear()
        }

        view.buttonCancel.setOnClickListener{
            view.editTextHospitalName.text?.clear()
            view.editTextAddress.text?.clear()
            view.editTextDescription.text?.clear()
            view.editTextLatitude.text?.clear()
            view.editTextLongitude.text?.clear()
        }

        return view
    }
}