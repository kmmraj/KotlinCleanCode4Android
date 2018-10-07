/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

package com.example.kotlincleancode4android.homescreen

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.kotlincleancode4android.FlightViewModel
import com.example.kotlincleancode4android.R
import java.util.ArrayList


 interface HomeActivityInput {
    fun displayHomeMetaData(viewModel: HomeViewModel)
}

//interface HomeRouterOutput {
//    ArrayList<FlightViewModel> listOfVMFlights = null;
//     HomeRouter router = null;
//}


class HomeActivity : AppCompatActivity(), HomeActivityInput {

    lateinit var listOfVMFlights: ArrayList<FlightViewModel>
    lateinit var output: HomeInteractorInput
    lateinit var router: HomeRouter



//    var output: HomeInteractorInput? = null
//        set(value) {
//            if (field == null) field = value
//        }
//    var router: HomeRouter? = null
//        set(value) {
//        if (field == null) field = value
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        HomeConfigurator.configure(this)
        fetchMetaData()
        createFlightListView()
    }

    fun fetchMetaData() {
        // create Request and set the needed input
        val homeRequest = HomeRequest()
        homeRequest.isFutureTrips = true
        // Call the output to fetch the data
        output.fetchHomeMetaData(homeRequest)
    }

    private fun createFlightListView() {
        val listView = findViewById<ListView>(R.id.listOfFlights)
        listView.adapter = FlightListAdapter()
        listView.isClickable = true
        listView.onItemClickListener = router
    }

    override fun displayHomeMetaData(viewModel: HomeViewModel) {
        Log.e(TAG, "displayHomeMetaData() called with: viewModel = [$viewModel]")
        listOfVMFlights = viewModel.listOfFlights!!
    }


    private inner class FlightListAdapter() : BaseAdapter() {

        private val layoutInflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return listOfVMFlights.size
        }

        override fun getItem(position: Int): Any {
            return listOfVMFlights[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView

            if (view == null) {
                view = layoutInflater.inflate(R.layout.cell_trip_list, null)
                view?.let{
                    val viewHolder = ViewHolder()
                    viewHolder.flightNameTextView = view.findViewById(R.id.tv_flightNumberValue) as TextView
                    viewHolder.startTimeTextView = view.findViewById(R.id.tv_flightTimeDescription) as TextView
                    view.tag = viewHolder
                }
            }
            val viewHolder = view?.tag as ViewHolder
            viewHolder.flightNameTextView?.text = listOfVMFlights[position].flightName
            viewHolder.startTimeTextView?.text = listOfVMFlights[position].noOfDaysToFly
            return view
        }
    }

    internal inner class ViewHolder {
        var flightNameTextView: TextView? = null
        var startTimeTextView: TextView? = null
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}
