package com.example.kotlincleancode4android.homescreen

import com.example.kotlincleancode4android.FlightModel
import java.util.ArrayList

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */
interface FlightWorkerInput {
    val futureFlights: ArrayList<FlightModel>
    val pastFlights: ArrayList<FlightModel>
}

class FlightWorker : FlightWorkerInput {

    override val futureFlights: ArrayList<FlightModel>
        get() {

            val flightsList = ArrayList<FlightModel>()

            val flight1 = FlightModel(flightName = "9Z 231",
                    startingTime = "2018/10/31",
                    departureCity = "BLR",
                    arrivalCity = "CJB",
                    departureTime = "06:00",
                    arrivalTime = "06:50")

            flightsList.add(flight1)

            val flight2 = FlightModel(flightName = "9Z 15",
                    startingTime = "2017/02/31",
                    departureCity = "BLR",
                    arrivalCity = "CJB",
                    departureTime = "09:00",
                    arrivalTime = "09:50")
            flightsList.add(flight2)

            val flight3 = FlightModel(flightName = "9Z 142",
                    startingTime = "2017/12/31",
                    departureCity = "BLR",
                    arrivalCity = "CJB",
                    departureTime = "18:10",
                    arrivalTime = "19:00")
            flightsList.add(flight3)

            return flightsList
        }

    override val pastFlights: ArrayList<FlightModel>
        get() {

            val flightsList = ArrayList<FlightModel>()

            val flight1 = FlightModel(flightName = "9Z 231",
                    startingTime = "2015/10/31",
                    departureCity = "BLR",
                    arrivalCity = "CJB",
                    departureTime = "06:00",
                    arrivalTime = "06:50")

            flightsList.add(flight1)

            val flight2 = FlightModel(flightName = "9Z 15",
                    startingTime = "2015/11/31",
                    departureCity = "BLR",
                    arrivalCity = "CJB",
                    departureTime = "09:00",
                    arrivalTime = "09:50")
            flightsList.add(flight2)

            val flight3 = FlightModel(flightName = "9Z 142",
                    startingTime = "2015/12/31",
                    departureCity = "BLR",
                    arrivalCity = "CJB",
                    departureTime = "18:10",
                    arrivalTime = "19:00")
            flightsList.add(flight3)

            return flightsList
        }

}