package com.example.kotlincleancode4android

import com.example.kotlincleancode4android.boardingScreen.BoardingActivity
import com.example.kotlincleancode4android.homescreen.HomeFragment
import com.example.kotlincleancode4android.homescreen.MainActivity
import com.example.kotlincleancode4android.homescreen.HomeRouter
import com.example.kotlincleancode4android.pastTripScreen.PastTripFragment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
@RunWith(RobolectricTestRunner::class)
class HomeRouterUnitTest {

   @Test
    fun homeRouter_determineNextScreen_when_futureTripIs_Input() {
        // Given
        val homeRouter = HomeRouter()
        val flightList = ArrayList<FlightViewModel>()
        val flight1 = FlightViewModel(
                flightName = "9Z 231",
                startingTime = "2017/12/31",
                departureCity = "BLR",
                arrivalCity = "CJB",
                departureTime = "18:10",
                arrivalTime = "19:00"
        )
        flightList.add(flight1)

        val flight2 = FlightViewModel(
                flightName = "9Z 222",
                startingTime = "2016/12/31",
                departureCity = "BLR",
                arrivalCity = "CJB",
                departureTime = "18:10",
                arrivalTime = "19:00"
        )
        flightList.add(flight2)

        val homeActivity = Robolectric.setupActivity(MainActivity::class.java)
        val homeFragment = HomeFragment()
        homeFragment.listOfVMFlights = flightList
        homeFragment.router = homeRouter
        homeRouter.fragment = WeakReference(homeFragment)

        val currentTime = Calendar.getInstance()
        currentTime.set(2017, 5, 30, 0, 0, 0)
        homeRouter.currentTime = currentTime

        // When - Future Trip is Input
        val fragment = homeRouter.determineNextScreen(0)

        // Then
        val targetFragmentName = fragment.javaClass.name
        Assert.assertEquals("When the future travel date is passed to HomeRouter" +
                " Then next Fragment should be BoardingFragment",
                targetFragmentName,
                BoardingActivity::class.java.name)
    }

    @Test
    fun homeRouter_determineNextScreen_when_pastTripIs_Input() {
        // Given
        val homeRouter = HomeRouter()
        val flightList = ArrayList<FlightViewModel>()
        val flight1 = FlightViewModel(
                flightName = "9Z 231",
                startingTime = "2017/12/31",
                departureCity = "BLR",
                arrivalCity = "CJB",
                departureTime = "18:10",
                arrivalTime = "19:00"
        )
        flightList.add(flight1)

        val flight2 = FlightViewModel(
                flightName = "9Z 222",
                startingTime = "2016/12/31",
                departureCity = "BLR",
                arrivalCity = "CJB",
                departureTime = "18:10",
                arrivalTime = "19:00"
        )
        flightList.add(flight2)

        val homeFragment = HomeFragment()
        homeFragment.listOfVMFlights = flightList
        homeFragment.router = homeRouter
        homeRouter.fragment = WeakReference(homeFragment)

        val currentTime = Calendar.getInstance()
        currentTime.set(2017, 5, 30, 0, 0, 0)
        homeRouter.currentTime = currentTime

        // When - Past Trip is Input
        val fragment = homeRouter.determineNextScreen(1)

        // Then
        val targetFragmentName = fragment.javaClass.name
        Assert.assertEquals("When the past travel date is passed to HomeRouter"
                + " Then next Intent should be PastTripFragment",
                targetFragmentName, PastTripFragment::class.java.name)
    }

    companion object {
        const val TAG = "HomeRouterUnitTest"
    }
}