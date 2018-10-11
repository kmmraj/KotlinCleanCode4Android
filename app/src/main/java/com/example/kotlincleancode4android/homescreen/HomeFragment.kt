package com.example.kotlincleancode4android.homescreen


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.kotlincleancode4android.FlightViewModel
import com.example.kotlincleancode4android.R
import java.util.ArrayList

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */

interface HomeFragmentInput {
    fun displayHomeMetaData(viewModel: HomeViewModel)
}

class HomeFragment : Fragment(), HomeFragmentInput {

    var listOfVMFlights: ArrayList<FlightViewModel> = arrayListOf()
    lateinit var output: HomeInteractorInput
    lateinit var router: HomeRouter
    lateinit var homeFragmentListener: HomeFragmentListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        HomeConfigurator.configureFragment(this)
        fetchData()
        createFlightListView(view)

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            homeFragmentListener = activity as HomeFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement HomeFragmentListener")
        }
    }

    fun fetchData() {
        // create Request and set the needed input
        val homeRequest = HomeRequest()
        homeRequest.isFutureTrips = true
        // Call the output to fetch the data
        output.fetchHomeMetaData(homeRequest)
    }

    private fun createFlightListView(view: View) {
        val listView = view.findViewById<ListView>(R.id.listOfFlights)
        listView.adapter = FlightListAdapter(this.requireContext(),listOfVMFlights)
        listView.isClickable = true
        listView.onItemClickListener = router
    }

    override fun displayHomeMetaData(viewModel: HomeViewModel) {
        Log.d(HomeFragment.TAG, "displayHomeMetaData() called with: viewModel = [$viewModel]")
        listOfVMFlights = viewModel.listOfFlights!!
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}

interface HomeFragmentListener{
    fun startPastTripFragment(fragment: Fragment)
}
