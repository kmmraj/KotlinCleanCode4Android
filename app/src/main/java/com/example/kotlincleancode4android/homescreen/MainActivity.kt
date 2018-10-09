/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

package com.example.kotlincleancode4android.homescreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.kotlincleancode4android.R
import com.example.kotlincleancode4android.transact


class MainActivity : AppCompatActivity(),HomeActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        showFragment(HomeFragment())
    }

    private fun showFragment(fragment: Fragment) {
        transact {
            replace(R.id.container, fragment)
            setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }

    override fun startPastTripFragment(fragment: Fragment) {
       showFragment(fragment)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
