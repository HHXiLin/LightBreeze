package com.lightbreeze.android.logic.network

import com.lightbreeze.android.logic.model.CityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CityService {
    @GET
    fun searchCities(@Url url:String, @Query("location") location:String,@Query("key") key:String): Call<CityResponse>
}