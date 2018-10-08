package com.example.kotlincleancode4android

import com.example.kotlincleancode4android.homescreen.HomeActivity
import com.example.kotlincleancode4android.homescreen.HomeInteractorInput
import com.example.kotlincleancode4android.homescreen.HomeRequest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by Mohanraj Karatadipalayam on 07/10/18.
 */
@RunWith(RobolectricTestRunner::class)
class HomeActivityUnitTest {

    @Test
    fun homeActivity_ShouldNOT_be_Null() {
        // Given
        val activity = Robolectric.setupActivity(HomeActivity::class.java)
        // When

        // Then
        Assert.assertNotNull(activity)
    }

    @Test
    fun onCreate_shouldCall_fetchHomeMetaData() {
        // Given
        val homeActivityOutputSpy = HomeActivityOutputSpy()
        val homeActivity = Robolectric.setupActivity(HomeActivity::class.java)
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        homeActivity.output = homeActivityOutputSpy

        // When
        homeActivity.fetchMetaData()

        // Then
        Assert.assertTrue(homeActivityOutputSpy.fetchHomeMetaDataIsCalled)
    }

    @Test
    fun onCreate_Calls_fetchHomeMetaData_withCorrectData() {
        // Given
        val homeActivityOutputSpy = HomeActivityOutputSpy()
        val homeActivity = Robolectric.setupActivity(HomeActivity::class.java)
        homeActivity.output = homeActivityOutputSpy

        // When
        homeActivity.fetchMetaData()

        // Then
        Assert.assertNotNull(homeActivity)
        Assert.assertTrue(homeActivityOutputSpy.homeRequestCopy.isFutureTrips)
    }

    private inner class HomeActivityOutputSpy : HomeInteractorInput {

        var fetchHomeMetaDataIsCalled = false
        lateinit var homeRequestCopy: HomeRequest

        override fun fetchHomeMetaData(request: HomeRequest) {
            fetchHomeMetaDataIsCalled = true
            homeRequestCopy = request
        }
    }
}
