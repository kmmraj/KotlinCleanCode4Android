package com.example.kotlincleancode4android.homescreen

import android.util.Log
import com.example.kotlincleancode4android.ArrayEmptyException

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

interface HomeInteractorInput {
    fun fetchHomeMetaData(request: HomeRequest)
}

class HomeInteractor : HomeInteractorInput {

    var output: HomePresenterInput? = null

    var flightWorkerInput: FlightWorkerInput? = null


    fun retrieveFlightWorkerInput(): FlightWorkerInput {
        return flightWorkerInput ?: FlightWorker()
    }

//    fun setFlightWorkerInput(flightWorkerInput: FlightWorkerInput) {
//        this.flightWorkerInput = flightWorkerInput
//    }

    override fun fetchHomeMetaData(request: HomeRequest) {
        Log.e(TAG, "In method fetchHomeMetaData")
        flightWorkerInput = retrieveFlightWorkerInput()
        val homeResponse = HomeResponse()
        if (request.isFutureTrips) {
            homeResponse.listOfFlights = flightWorkerInput!!.futureFlights
        } else {
            homeResponse.listOfFlights = flightWorkerInput!!.pastFlights
        }
        //TODO : Add failure case here
        if (null == homeResponse.listOfFlights || homeResponse.listOfFlights!!.isEmpty()) {
            throw ArrayEmptyException("Empty Flight List")
        }

        output!!.presentHomeMetaData(homeResponse)
    }

    companion object {
        const val TAG = "HomeInteractor"
    }
}