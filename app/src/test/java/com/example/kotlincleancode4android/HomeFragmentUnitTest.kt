package com.example.kotlincleancode4android

import com.example.kotlincleancode4android.homescreen.HomeFragment
import com.example.kotlincleancode4android.homescreen.MainFragment
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
class HomeFragmentUnitTest {

    @Test
    fun homeActivity_ShouldNOT_be_Null() {
        // Given
        val activity = Robolectric.setupActivity(MainFragment::class.java)
        // When

        // Then
        Assert.assertNotNull(activity)
    }

    @Test
    fun onCreate_shouldCall_fetchHomeMetaData() {
        // Given
        val homeActivityOutputSpy = HomeFragmentOutputSpy()
       // val homeActivity = Robolectric.setupActivity(MainFragment::class.java)

        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val homeFragment = HomeFragment()
        homeFragment.output = homeActivityOutputSpy

        // When
        homeFragment.fetchData()

        // Then
        Assert.assertTrue(homeActivityOutputSpy.fetchHomeMetaDataIsCalled)
    }

    @Test
    fun onCreate_Calls_fetchHomeMetaData_withCorrectData() {
        // Given
        val homeActivityOutputSpy = HomeFragmentOutputSpy()
        val homeFragment = HomeFragment()
        homeFragment.output = homeActivityOutputSpy

        // When
        homeFragment.fetchData()

        // Then
        Assert.assertNotNull(homeFragment)
        Assert.assertTrue(homeActivityOutputSpy.homeRequestCopy.isFutureTrips)
    }

    private inner class HomeFragmentOutputSpy : HomeInteractorInput {

        var fetchHomeMetaDataIsCalled = false
        lateinit var homeRequestCopy: HomeRequest

        override fun fetchHomeMetaData(request: HomeRequest) {
            fetchHomeMetaDataIsCalled = true
            homeRequestCopy = request
        }
    }
}
