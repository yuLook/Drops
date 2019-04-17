package com.example.module_base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log

class LocationUtils {

    var latitude : Double = 0.0
    var longitude : Double = 0.0

    companion object {
        val instance = LocationUtils()
    }

    @SuppressLint("MissingPermission")
    fun startLocation(context: Context){

        var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            var location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location!=null){
                latitude = location.latitude
                longitude = location.longitude
                Log.d("====", "定位成功 latitude:$latitude longitude:$longitude")
                return
            }
        }else{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0 as Float,object : LocationListener {
                override fun onLocationChanged(location: Location?) {
                    if (location!=null){
                        latitude = location.latitude
                        longitude = location.longitude
                        Log.d("====", "定位成功 latitude:$latitude longitude:$longitude")
                    }else{
                        Log.d("====", "定位失败 latitude:$latitude longitude:$longitude")
                    }
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                }

                override fun onProviderEnabled(provider: String?) {
                }

                override fun onProviderDisabled(provider: String?) {
                }

            })
            var location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location!=null){
                latitude = location.latitude
                longitude = location.longitude
                Log.d("====", "定位成功 latitude:$latitude longitude:$longitude")
                return
            }
        }

        Log.d("====", "定位失败 latitude:$latitude longitude:$longitude")

    }

}