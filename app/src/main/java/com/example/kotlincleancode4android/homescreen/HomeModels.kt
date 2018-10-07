package com.example.kotlincleancode4android.homescreen

import com.example.kotlincleancode4android.FlightModel
import com.example.kotlincleancode4android.FlightViewModel
import java.util.ArrayList

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */
data class HomeViewModel (
    //TODO - filter to have only the needed data
    var listOfFlights: ArrayList<FlightViewModel>? = null
)

data class HomeRequest( var isFutureTrips: Boolean = false)


data class HomeResponse(var listOfFlights: ArrayList<FlightModel>? = null)
