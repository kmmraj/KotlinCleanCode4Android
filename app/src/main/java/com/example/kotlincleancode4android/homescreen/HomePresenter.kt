package com.example.kotlincleancode4android.homescreen

import android.util.Log
import com.example.kotlincleancode4android.FlightViewModel
import java.lang.ref.WeakReference
import java.util.Calendar
import java.util.concurrent.TimeUnit

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */
interface HomePresenterInput {
    fun presentHomeMetaData(response: HomeResponse)
}

class HomePresenter : HomePresenterInput {

    var output: WeakReference<HomeActivityInput>? = null
    var currentTime: Calendar? = null
        get() = if (field == null) Calendar.getInstance() else field

    override fun presentHomeMetaData(response: HomeResponse) {
        // Log.e(TAG, "presentHomeMetaData() called with: response = [" + response + "]");
        // Do your decoration or filtering here
        val homeViewModel = HomeViewModel()
        homeViewModel.listOfFlights = ArrayList()

        if (response.listOfFlights != null) {

            for (fm in response.listOfFlights!!) {
                val fvm = FlightViewModel(noOfDaysToFly = null,
                        departureCity = fm.departureCity,
                        arrivalCity = fm.arrivalCity,
                        flightName = fm.flightName,
                        startingTime = fm.startingTime,
                        departureTime = fm.departureTime,
                        arrivalTime = fm.arrivalTime)

                // Decoration
                val startingTime = getCalendar(fvm.startingTime)
                startingTime?.let {
                    val startTime = it
                    currentTime?.let {
                    val daysDiff = getDaysDiff(it.timeInMillis, startTime.timeInMillis)
                    setDaysFlyDecorationText(fvm, daysDiff, it.timeInMillis, startTime.timeInMillis)
                    }
                }
                homeViewModel.listOfFlights?.add(fvm)
            }
            output?.get()?.displayHomeMetaData(homeViewModel)
        }
    }

    private fun setDaysFlyDecorationText(fvm: FlightViewModel, daysDiff: Long, startTime: Long, endTime: Long) {
        if (endTime > startTime) {
            fvm.noOfDaysToFly = "You have $daysDiff days to fly"
        } else {
            // daysDiff =-daysDiff;
            fvm.noOfDaysToFly = "It has been $daysDiff days since you flew"
        }
    }

    private fun getCalendar(date: String?): Calendar? {
        // Date should be in the format YYYY/MM/DD if not return
        if (date != null && !date.isEmpty() && date.length == 10) {
            val year = Integer.parseInt(date.substring(0, 4))
            val month = Integer.parseInt(date.substring(5, 7))
            val day = Integer.parseInt(date.substring(8, 10))
            val startingTime = Calendar.getInstance()
            startingTime.set(year, month - 1, day, 0, 0, 0)
            return startingTime
        }
        return null
    }

    private fun getDaysDiff(startTime: Long, endTime: Long): Long {
        val msDiff: Long
        if (endTime > startTime) {
            msDiff = endTime - startTime
        } else {
            msDiff = startTime - endTime
        }
        val daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff)
        Log.e(TAG, "diff is  $daysDiff")
        return daysDiff
    }

    companion object {
        const val TAG = "HomePresenter"
    }
}