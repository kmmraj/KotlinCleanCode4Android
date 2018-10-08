package com.example.kotlincleancode4android.boardingScreen

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import java.lang.ref.WeakReference

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
internal interface BoardingRouterInput {
    fun navigateToSomeWhere(position: Int): Intent
    fun passDataToNextScene(position: Int, intent: Intent)
}

class BoardingRouter : BoardingRouterInput, AdapterView.OnItemClickListener {

    var activity: WeakReference<BoardingActivity>? = null

    override fun navigateToSomeWhere(position: Int): Intent {
        // Based on the position or some other data decide what is the next scene
        // Intent intent = new Intent(activity.get(),NextActivity.class);
        // return intent;
        return Intent()
    }

    override fun passDataToNextScene(position: Int, intent: Intent) {
        // Based on the position or some other data decide the data for the next scene
        // BoardingModel flight = activity.get().listOfSomething.get(position);
        // intent.putExtra("flight",flight);
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        val intent = navigateToSomeWhere(position)
        passDataToNextScene(position, intent)
        activity?.get()?.startActivity(intent)
    }

    companion object {
        const val TAG = "BoardingRouter"
    }
}