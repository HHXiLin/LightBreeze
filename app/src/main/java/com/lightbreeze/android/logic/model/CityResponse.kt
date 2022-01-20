package com.lightbreeze.android.logic.model

import com.google.gson.annotations.SerializedName

data class CityResponse (@SerializedName("code") val status:String,
                         @SerializedName("location")val cities:List<City>)

data class City(val name:String,val id:String,val lat:String,val lon:String,
                @SerializedName("adm2") val adm:String,
                @SerializedName("adm1") val belong:String, val country:String,
                @SerializedName("tz") val timeZone:String, val utcOffset:String)
