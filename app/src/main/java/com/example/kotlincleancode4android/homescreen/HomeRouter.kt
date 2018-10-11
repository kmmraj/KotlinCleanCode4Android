package com.example.kotlincleancode4android.homescreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.example.kotlincleancode4android.CalendarUtil
import com.example.kotlincleancode4android.pastTripScreen.PastTripFragment
import java.lang.ref.WeakReference
import java.util.Calendar

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

internal interface HomeRouterInput {
    fun determineNextScreen(position: Int): Fragment
    fun passDataToNextScene(position: Int, nextFragment: Fragment)
}

class HomeRouter : HomeRouterInput, AdapterView.OnItemClickListener {
    var fragment: WeakReference<HomeFragment>? = null
    var currentTime: Calendar? = null
        get() = if (field == null) Calendar.getInstance() else field


    override fun determineNextScreen(position: Int): Fragment {
        // Based on the position or some other data decide what is the next scene

        val flight = fragment?.get()?.listOfVMFlights?.get(position)
        val startingTime = CalendarUtil.getCalendar(flight?.startingTime)

        return if (isFutureFlight(startingTime)) {
           // Intent(activity!!.get(), BoardingActivity::class.java)
            HomeFragment()
        } else {
//            Intent(activity!!.get(), PastTripFragment::class.java)
            PastTripFragment()
        }
    }

    override fun passDataToNextScene(position: Int, nextFragment: Fragment) {
        // Based on the position or some other data decide the data for the next scene
        val flight = fragment?.get()?.listOfVMFlights?.get(position)
        val args =  Bundle()
        args.putParcelable("flight",flight)
        nextFragment.arguments = args
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Log.d(TAG, "onItemClick() called with: parent = [$parent], view = [$view], position = [$position], id = [$id]");
        val nextFragment = determineNextScreen(position)
        passDataToNextScene(position, nextFragment)
        fragment?.get()?.homeFragmentListener?.startPastTripFragment(nextFragment)
        // TODO - Decide should we start the fragment from here or from the activity ?
    }

    private fun isFutureFlight(startingTime: Calendar): Boolean {
        val startTimeInMills = startingTime.timeInMillis
        val currentTimeInMills = currentTime?.timeInMillis
        currentTimeInMills?.let {
            return startTimeInMills >= currentTimeInMills
        }
        return false
    }

    companion object {
        const val TAG = "HomeRouter"
    }
}