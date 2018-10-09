package com.example.kotlincleancode4android.homescreen

import java.lang.ref.WeakReference

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

object HomeConfigurator {

    fun configureFragment(fragment: HomeFragment) {

        val router = HomeRouter()
        router.fragment = WeakReference(fragment)

        val presenter = HomePresenter()
        presenter.output = WeakReference(fragment)

        val interactor = HomeInteractor()
        interactor.output = presenter

        fragment.output = interactor
        fragment.router = router
    }
}