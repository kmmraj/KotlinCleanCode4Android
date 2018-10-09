package com.example.kotlincleancode4android.pastTripScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlincleancode4android.FlightModel
import com.example.kotlincleancode4android.R

class PastTripFragment : Fragment() {

    private var flightModel: FlightModel? = null

    private var passengerName: TextView? = null
    private var departureCity: TextView? = null
    private var arrivalCity: TextView? = null
    private var boardingTime: TextView? = null
    private var departureTime: TextView? = null
    private var arrivalTime: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_past_trip, container, false)
        flightModel = this.arguments?.getParcelable("flight")
        bindViews(view)
        displayTripData(flightModel)
        return view
    }

    private fun bindViews(view: View) {
        passengerName = view.findViewById(R.id.tv_passengerName)
        departureCity = view.findViewById(R.id.tv_departureAirport)
        arrivalCity = view.findViewById(R.id.tv_arrivalAirport)
        boardingTime = view.findViewById(R.id.tv_boardingTime)
        departureTime = view.findViewById(R.id.tv_departureTime)
        arrivalTime = view.findViewById(R.id.tv_arrivalTime)
    }

    private fun displayTripData(fightModel: FlightModel?) {
        Log.e(TAG, "displayBoardingData() called with: viewModel = [$fightModel]")
        // Deal with the data
        passengerName?.text = "Mr. Mohan Karats"
        // mFlightCode.setText(flightModel.flightName);
        arrivalCity?.text = flightModel?.arrivalCity
        arrivalTime?.text = flightModel?.arrivalTime
        departureCity?.text = flightModel?.departureCity
        departureTime?.text = flightModel?.departureTime
    }

    companion object {
        const val TAG = "PastTripFragment"
    }
}