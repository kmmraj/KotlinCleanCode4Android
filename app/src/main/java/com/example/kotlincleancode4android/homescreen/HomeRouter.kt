package com.example.kotlincleancode4android.homescreen

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import com.example.kotlincleancode4android.CalendarUtil
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

internal interface HomeRouterInput {
    fun determineNextScreen(position: Int): Intent
    fun passDataToNextScene(position: Int, intent: Intent)
}

class HomeRouter : HomeRouterInput, AdapterView.OnItemClickListener {
    var activity: WeakReference<HomeActivity>? = null
    var currentTime: Calendar? = null
        get() = if (field == null) Calendar.getInstance() else field


    override fun determineNextScreen(position: Int): Intent {
        //Based on the position or someother data decide what is the next scene

        val flight = activity?.get()?.listOfVMFlights?.get(position)
        val startingTime = CalendarUtil.getCalendar(flight?.startingTime)

//        return if (isFutureFlight(startingTime)) {
//            Intent(activity!!.get(), BoardingActivity::class.java)
//        } else {
//            Intent(activity!!.get(), PastTripActivity::class.java)
//        }
        return Intent()
    }

    override fun passDataToNextScene(position: Int, intent: Intent) {
        //Based on the position or someother data decide the data for the next scene
        val flight = activity?.get()?.listOfVMFlights?.get(position)
        intent.putExtra("flight", flight)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        val intent = determineNextScreen(position)
        passDataToNextScene(position, intent)
        activity?.get()?.startActivity(intent)
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

        var TAG = HomeRouter::class.java.simpleName
    }

}