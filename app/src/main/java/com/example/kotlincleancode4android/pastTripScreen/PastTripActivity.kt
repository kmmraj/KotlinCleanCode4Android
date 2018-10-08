package com.example.kotlincleancode4android.pastTripScreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.kotlincleancode4android.FlightModel
import com.example.kotlincleancode4android.R

class PastTripActivity : AppCompatActivity() {


    private var flightModel: FlightModel? = null

    private var passengerName: TextView? = null
    private var departureCity: TextView? = null
    private var arrivalCity: TextView? = null
    private var boardingTime: TextView? = null
    private var departureTime: TextView? = null
    private var arrivalTime: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_trip)
        supportActionBar?.hide()
        flightModel = intent.getParcelableExtra("flight")
        bindViews()
        displayTripData(flightModel)
    }

    private fun bindViews() {
        passengerName = findViewById(R.id.tv_passengerName)
        departureCity = findViewById(R.id.tv_departureAirport)
        arrivalCity = findViewById(R.id.tv_arrivalAirport)
        boardingTime = findViewById(R.id.tv_boardingTime)
        departureTime = findViewById(R.id.tv_departureTime)
        arrivalTime = findViewById(R.id.tv_arrivalTime)
        // mDepartureDate = (TextView) findViewById(R.id.tv_departureDate);
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
        const val TAG = "PastTripActivity"
    }
}