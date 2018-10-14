package com.example.kotlincleancode4android

import com.example.kotlincleancode4android.homescreen.HomeFragment
import com.example.kotlincleancode4android.homescreen.MainActivity
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
    fun mainActivity_ShouldNOT_be_Null() {
        // Given
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        // When

        // Then
        Assert.assertNotNull(activity)
    }

    @Test
    fun fragment_ShouldNOT_be_Null() {
        // Given
        val fragment = HomeFragment()
        // When

        // Then
        Assert.assertNotNull(fragment)
    }

    @Test
    fun onCreateView_shouldCall_fetchHomeData() {
        // Given
        val fragmentOutputSpy = HomeFragmentOutputSpy()

        // It must have called the onCreateView earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val homeFragment = HomeFragment()
        homeFragment.output = fragmentOutputSpy

        // When
        homeFragment.fetchData()

        // Then
        Assert.assertTrue(fragmentOutputSpy.fetchHomeDataIsCalled)
    }

    @Test
    fun onCreateView_Calls_fetchHomeMetaData_withCorrectData() {
        // Given
        val fragmentOutputSpy = HomeFragmentOutputSpy()
        val homeFragment = HomeFragment()
        homeFragment.output = fragmentOutputSpy

        // When
        homeFragment.fetchData()

        // Then
        Assert.assertNotNull(homeFragment)
        Assert.assertTrue(fragmentOutputSpy.homeRequestCopy.isFutureTrips)
    }

    private inner class HomeFragmentOutputSpy : HomeInteractorInput {

        var fetchHomeDataIsCalled = false
        lateinit var homeRequestCopy: HomeRequest

        override fun fetchHomeData(request: HomeRequest) {
            fetchHomeDataIsCalled = true
            homeRequestCopy = request
        }
    }
}
