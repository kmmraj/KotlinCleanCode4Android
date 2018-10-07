package com.example.kotlincleancode4android.homescreen

import java.lang.ref.WeakReference

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

object HomeConfigurator {

    fun configure(activity: HomeActivity) {

        val router = HomeRouter()
        router.activity = WeakReference(activity)

        val presenter = HomePresenter()
        presenter.output = WeakReference(activity)

        val interactor = HomeInteractor()
        interactor.output = presenter

       // activity.output?.let {  }

        activity.output = interactor

//        if (activity.output == null) {
//            activity.output = interactor
//        }
        activity.router = router
//        if (activity.router == null) {
//            activity.router = router
//        }
    }

}