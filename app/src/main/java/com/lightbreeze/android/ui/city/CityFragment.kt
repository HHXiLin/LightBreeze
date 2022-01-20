package com.lightbreeze.android.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightbreeze.android.R
import kotlinx.android.synthetic.main.fragment_city.*

class CityFragment:Fragment() {
    val viewModel by lazy{ViewModelProvider(this).get(CityViewModel::class.java)}

    private lateinit var adapter:CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager=LinearLayoutManager(activity)
        recyclerView.layoutManager=layoutManager
        adapter= CityAdapter(this,viewModel.cityList)
        recyclerView.adapter=adapter

        searchCityEdit.addTextChangedListener { editable->
            val content=editable.toString()
            if(content.isNotEmpty()){
                viewModel.searchCities(content)
            }else{
                recyclerView.visibility=View.GONE
                bgImageView.visibility=View.VISIBLE
                viewModel.cityList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.cityLiveData.observe(viewLifecycleOwner, Observer{ result->
            val cities=result.getOrNull()
            if(cities!=null){
                recyclerView.visibility=View.VISIBLE
                bgImageView.visibility=View.GONE
                viewModel.cityList.clear()
                viewModel.cityList.addAll(cities)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}