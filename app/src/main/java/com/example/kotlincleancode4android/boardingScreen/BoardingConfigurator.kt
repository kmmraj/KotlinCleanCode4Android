package com.example.kotlincleancode4android.boardingScreen

import java.lang.ref.WeakReference

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */

object BoardingConfigurator {

    fun configure(activity: BoardingActivity) {

        val router = BoardingRouter()
        router.activity = WeakReference(activity)

        val presenter = BoardingPresenter()
        presenter.output = WeakReference(activity)

        val interactor = BoardingInteractor()
        interactor.output = presenter

//        if (activity.output == null) {
            activity.output = interactor
//        }
//        if (activity.router == null) {
            activity.router = router
//        }
    }
}