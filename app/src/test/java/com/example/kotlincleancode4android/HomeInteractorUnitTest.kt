package com.example.kotlincleancode4android

import com.example.kotlincleancode4android.homescreen.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.ArrayList

/**
 * Created by Mohanraj Karatadipalayam on 07/10/18.
 */
@RunWith(RobolectricTestRunner::class)
class HomeInteractorUnitTest {

    @Test
    fun fetchHomeMetaData_with_validInput_shouldCall_presentHomeMetaData() {
        // Given
        val homeInteractor = HomeInteractor()
        val homeRequest = HomeRequest()
        homeRequest.isFutureTrips = true
        val homePresenterInputSpy = HomePresenterInputSpy()
        homeInteractor.output = homePresenterInputSpy
        // When
        homeInteractor.fetchHomeMetaData(homeRequest)

        // Then
        Assert.assertTrue("When the valid input is passed to HomeInteractor " + "Then presentHomeMetaData should be called",
                homePresenterInputSpy.presentHomeMetaDataIsCalled)
    }

    @Test
    fun fetchHomeMetaData_with_validInput_FutureTrip_shouldCall_Worker_getFutureTrips() {
        // Given
        val homeInteractor = HomeInteractor()
        val homeRequest = HomeRequest()
        homeRequest.isFutureTrips = true

        // Setup TestDoubles
        homeInteractor.output = HomePresenterInputSpy()
        val flightWorkerInputSpy = FlightWorkerInputSpy()
        homeInteractor.flightWorkerInput = flightWorkerInputSpy

        // When
        homeInteractor.fetchHomeMetaData(homeRequest)

        // Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" + "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isGetFutureFlightsMethodCalled)
    }

    @Test
    fun fetchHomeMetaData_with_validInput_PastTrip_shouldCall_Worker_getPastTrips() {
        // Given
        val homeInteractor = HomeInteractor()
        val homeRequest = HomeRequest()
        homeRequest.isFutureTrips = false

        // Setup TestDoubles
        homeInteractor.output = HomePresenterInputSpy()
        val flightWorkerInputSpy = FlightWorkerInputSpy()
        homeInteractor.flightWorkerInput = flightWorkerInputSpy

        // When
        homeInteractor.fetchHomeMetaData(homeRequest)

        // Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" + "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isGetPastFlightsMethodCalled)
    }


    @Test(expected = ArrayEmptyException::class)
    fun fetchHomeMetaData_fetchingNull_shouldThrowArrayEmptyException() {
        // Given
        val homeInteractor = HomeInteractor()
        val homeRequest = HomeRequest()
        homeRequest.isFutureTrips = false

        // Setup TestDoubles
        homeInteractor.output = HomePresenterInputSpy()
        val flightWorkerInputReturnNullSpy = FlightWorkerInputReturnNullSpy()
        homeInteractor.flightWorkerInput = flightWorkerInputReturnNullSpy

        // When
        homeInteractor.fetchHomeMetaData(homeRequest)

        // Then
        // Check for ArrayEmptyException -- See this method Annotation
    }

    private inner class HomePresenterInputSpy: HomePresenterInput {

        internal var presentHomeMetaDataIsCalled = false
        internal var homeResponseCopy: HomeResponse? = null
        override fun presentHomeMetaData(response: HomeResponse) {
            presentHomeMetaDataIsCalled = true
            homeResponseCopy = response
        }
    }

    private inner class FlightWorkerInputSpy : FlightWorkerInput {

        internal var isGetFutureFlightsMethodCalled = false
        internal var isGetPastFlightsMethodCalled = false

        override val futureFlights: ArrayList<FlightModel>
            get() {
                isGetFutureFlightsMethodCalled = true
                return flightModels
            }

        override val pastFlights: ArrayList<FlightModel>
            get() {
                isGetPastFlightsMethodCalled = true
                return flightModels
            }

        private val flightModels: ArrayList<FlightModel>
            get() {
                val flightsList = ArrayList<FlightModel>()
                val flight1 = FlightModel(
                        flightName = "9Z 231",
                        startingTime = "2018/10/31",
                        departureCity = "BLR",
                        arrivalCity = "CJB",
                        departureTime = "18:10",
                        arrivalTime = "19:00"
                )
                flightsList.add(flight1)
                return flightsList
            }
    }

    private inner class FlightWorkerInputReturnNullSpy : FlightWorkerInput {

        internal var isGetFlightsMethodCalled = false
        internal var isGetPastFlightsMethodCalled = false

        override val futureFlights: ArrayList<FlightModel> = arrayListOf()
            get() {
                isGetFlightsMethodCalled = true
                return field
            }

        override val pastFlights: ArrayList<FlightModel> = arrayListOf()
            get() {
                isGetPastFlightsMethodCalled = true
                return field
            }
    }
}