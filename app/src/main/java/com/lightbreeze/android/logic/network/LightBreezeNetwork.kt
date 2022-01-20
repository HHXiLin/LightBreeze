package com.lightbreeze.android.logic.network

import com.lightbreeze.android.LightBreezeApplication
import retrofit2.*
import java.lang.RuntimeException
import kotlin.coroutines.*

object LightBreezeNetwork {
    private val cityService=ServiceCreator.create<CityService>()

    suspend fun searchCities(query:String)= cityService
        .searchCities("https://geoapi.qweather.com/v2/city/lookup?",query,LightBreezeApplication.KEY).await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine{continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if(body !=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}