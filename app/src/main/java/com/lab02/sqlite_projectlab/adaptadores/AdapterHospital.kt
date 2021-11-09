package com.lab02.sqlite_projectlab.adaptadores

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lab02.sqlite_projectlab.R
import com.lab02.sqlite_projectlab.db.DB_Helper
import com.lab02.sqlite_projectlab.db.Hospital
import com.lab02.sqlite_projectlab.ui.EditFragment

class AdapterHospital: RecyclerView.Adapter<AdapterHospital.ViewHolder>(){


    var hospitals: MutableList<Hospital> = ArrayList()
    lateinit var context: Context

    fun AdapterHospital (_hospitals: MutableList<Hospital>, _context: Context){
        this.hospitals = _hospitals
        this.context = _context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder  {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_hospitales,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         holder.itemTitle.text = hospitals[position].getName()
         holder.itemAddress.text = hospitals[position].getAddress()
         holder.buttonEdit.setOnClickListener{
             editEvent(hospitals[position], it)
         }
        holder.buttonDelete.setOnClickListener{
            deleteEvent(hospitals[position])
        }
    }

    private fun editEvent(_hospital: Hospital, it: View) {
        val bundle = Bundle()
        bundle.putString("id", _hospital.getId().toString())
        bundle.putString("nombre", _hospital.getName().toString())
        bundle.putString("direccion",_hospital.getAddress().toString())
        bundle.putString("latitud",_hospital.getLatitude().toString())
        bundle.putString("longitud",_hospital.getLongitude().toString())
        bundle.putString("descripcion",_hospital.getDescription().toString())

        val editFragment = EditFragment()
        editFragment.arguments = bundle

        val appCompatActivity = it.context as AppCompatActivity
        appCompatActivity.supportFragmentManager.
        beginTransaction()
            .replace(R.id.fragment_container, editFragment)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteEvent(_hospital: Hospital){
        val db = DB_Helper(context)
        db.deleteItem(_hospital.getId())
        removeItem(_hospital)
    }

    private fun removeItem(hospital: Hospital) {
        val currPosition: Int = hospitals.indexOf(hospital)
        hospitals.remove(hospital)
        notifyItemRemoved(currPosition)
    }

    override fun getItemCount(): Int {
        return hospitals.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemAddress: TextView
        var buttonEdit: ImageButton
        var buttonDelete: ImageButton

        init {
            itemTitle = itemView.findViewById(R.id.titulo);
            itemAddress = itemView.findViewById(R.id.altitud);
            buttonEdit = itemView.findViewById(R.id.card_view_hospital_buttonEdit);
            buttonDelete = itemView.findViewById(R.id.card_view_hospital_buttonDelete);
        }

    }

}