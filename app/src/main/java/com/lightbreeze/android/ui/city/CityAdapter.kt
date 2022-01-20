package com.lightbreeze.android.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lightbreeze.android.R
import com.lightbreeze.android.logic.model.City

class CityAdapter(private val fragment: Fragment, private val cityList:List<City>) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>(){

        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val cityName: TextView =view.findViewById(R.id.cityName)
            val cityBelong:TextView=view.findViewById(R.id.cityBelong)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city=cityList[position]
        holder.cityName.text=city.name
        holder.cityBelong.text=city.belong
    }

    override fun getItemCount()=cityList.size
}