package com.lightbreeze.android.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lightbreeze.android.logic.Repository
import com.lightbreeze.android.logic.model.City

class CityViewModel: ViewModel() {
    private val searchLiveData=MutableLiveData<String>()

    val cityList=ArrayList<City>()

    val cityLiveData= Transformations.switchMap(searchLiveData){query->
        Repository.searchCities(query)
    }

    fun searchCities(query:String){
        searchLiveData.value=query
    }
}