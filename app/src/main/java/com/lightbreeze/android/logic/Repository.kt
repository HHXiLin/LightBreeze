package com.lightbreeze.android.logic

import androidx.lifecycle.liveData
import com.lightbreeze.android.logic.model.City
import com.lightbreeze.android.logic.network.LightBreezeNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

object Repository {
    fun searchCities(query:String)=liveData(Dispatchers.IO){
        val result=try{
            val cityResponse= LightBreezeNetwork.searchCities(query)
            if(cityResponse.status=="200"||cityResponse.status=="204"){
                val cities=cityResponse.cities
                Result.success(cities)
            }else{
                Result.failure(RuntimeException("response status is ${cityResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<City>>(e)
        }
        emit(result)
    }
}