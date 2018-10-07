package com.example.kotlincleancode4android

import android.util.Log
import com.example.kotlincleancode4android.homescreen.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Mohanraj Karatadipalayam on 07/10/18.
 */
@RunWith(RobolectricTestRunner::class)
class HomePresenterUnitTest {


    @Test
    fun presentHomeMetaData_with_validInput_shouldCall_displayHomeMetaData() {
        // Given
        val homePresenter = HomePresenter()
        val homeResponse = HomeResponse()
        homeResponse.listOfFlights = FlightWorker().futureFlights

        val homeActivityInputSpy = HomeActivityInputSpy()
        homePresenter.output = WeakReference(homeActivityInputSpy)

        // When
        homePresenter.presentHomeMetaData(homeResponse)

        // Then
        Assert.assertTrue("When the valid input is passed to HomePresenter Then displayHomeMetaData should be called", homeActivityInputSpy.isDisplayHomeMetaDataCalled)
    }

    @Test
    fun presentHomeMetaData_with_inValidInput_shouldNotCall_displayHomeMetaData() {
        // Given
        val homePresenter = HomePresenter()
        val homeResponse = HomeResponse()
        homeResponse.listOfFlights = null

        val homeActivityInputSpy = HomeActivityInputSpy()
        homePresenter.output = WeakReference<HomeActivityInput>(homeActivityInputSpy)

        // When
        homePresenter.presentHomeMetaData(homeResponse)

        // Then
        Assert.assertFalse("When the valid input is passed to HomePresenter Then displayHomeMetaData should NOT be called", homeActivityInputSpy.isDisplayHomeMetaDataCalled)
    }

    @Test
    fun verify_HomePresenter_getDaysDiff_is_CalculatedCorrectly_ForFutureTrips() {
        // Given
        val homePresenter = HomePresenter()
        val homeResponse = HomeResponse()

        val flightsList = ArrayList<FlightModel>()

        val flight1 = FlightModel(
                flightName = "9Z 231",
                startingTime = "2017/12/31",
                departureCity = "BLR",
                arrivalCity = "CJB",
                departureTime = "18:10",
                arrivalTime = "19:00"
        )
        flightsList.add(flight1)
        homeResponse.listOfFlights = flightsList

        val homeActivityInputSpy = HomeActivityInputSpy()
        homePresenter.output = WeakReference(homeActivityInputSpy)

        // When
        val currentTime = Calendar.getInstance()
        currentTime.set(2017, 5, 30, 0, 0, 0)
        homePresenter.currentTime = currentTime
        homePresenter.presentHomeMetaData(homeResponse)

        // Then
        // "It has been " + daysDiff + " days since you flew";
        val expectedText = "You have " + "184" + " days to fly"
        val actualText = homeActivityInputSpy.homeViewModelCopy.listOfFlights?.get(0)?.noOfDaysToFly
        Assert.assertEquals("When current date is 2016/10/12 & Flying Date is 2016/10/31 Then no of days should be 19", expectedText, actualText)

    }

    @Test
    fun verify_HomePresenter_getDaysDiff_isCalculatedCorrectly_ForPastTrips() {
        // Given
        val homePresenter = HomePresenter()
        val homeResponse = HomeResponse()

        val flightsList = ArrayList<FlightModel>()

        val flight1 = FlightModel(
                flightName = "9Z 231",
                startingTime = "2016/10/01",
                departureCity = "BLR",
                arrivalCity = "CJB",
                departureTime = "18:10",
                arrivalTime = "19:00"
        )

        flightsList.add(flight1)


        homeResponse.listOfFlights = flightsList

        val homeActivityInputSpy = HomeActivityInputSpy()
        homePresenter.output = WeakReference<HomeActivityInput>(homeActivityInputSpy)


        // When
        val currentTime = Calendar.getInstance()
        //currentTime.set(2017,5,30,0,0,0);
        currentTime.set(2017, 5, 30)
        Log.e(TAG, "verify_HomePresenter_getDaysDiff_isCalculatedCorrectly_ForPastTrips: " + currentTime.toString())
        homePresenter.currentTime = currentTime
        homePresenter.presentHomeMetaData(homeResponse)


        // Then
        // "It has been " + daysDiff + " days since you flew";
        val expectedText = "It has been " + 272 + " days since you flew"
        val actualText = homeActivityInputSpy.homeViewModelCopy.listOfFlights?.get(0)?.noOfDaysToFly
        Assert.assertEquals("When current date is 2017/05/30 & Flying Date is 2016/10/01 Then no of days should be 271", expectedText, actualText)

    }

    private inner class HomeActivityInputSpy : HomeActivityInput {
        var isDisplayHomeMetaDataCalled = false
        lateinit var homeViewModelCopy: HomeViewModel
        override fun displayHomeMetaData(viewModel: HomeViewModel) {
            isDisplayHomeMetaDataCalled = true
            homeViewModelCopy = viewModel
        }
    }

    companion object {
        const val TAG = "HomePresenterUnitTest"
    }
}