package com.example.kotlincleancode4android.boardingScreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kotlincleancode4android.FlightModel
import com.example.kotlincleancode4android.R

interface BoardingActivityInput {
    fun   displayBoardingData(viewModel: BoardingViewModel)
}


class BoardingActivity : AppCompatActivity(), BoardingActivityInput {

    var output: BoardingInteractorInput? = null
    var router: BoardingRouter? = null

    private var flightModel: FlightModel? = null
    private var passengerName: TextView? = null
    private var flightCode: TextView? = null
    private var departureCity: TextView? = null
    private var arrivalCity: TextView? = null
    private var boardingTime: TextView? = null
    private var departureTime: TextView? = null
    private var departureDate: TextView? = null
    private var arrivalTime: TextView? = null
    private var gate: TextView? = null
    private var terminal: TextView? = null
    private var seat: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)
        flightModel = intent.getParcelableExtra("flight")
        bindViews()

        // Do the setup
        BoardingConfigurator.configure(this)
        val aBoardingRequest = BoardingRequest()

        // Populate the request
        output?.fetchBoardingData(aBoardingRequest)

        // Do other work
    }

    private fun bindViews() {
        passengerName = findViewById(R.id.tv_passengerName)
        flightCode = findViewById(R.id.tv_flightNumberValue)
        departureCity = findViewById(R.id.tv_departureAirport)
        arrivalCity = findViewById(R.id.tv_arrivalAirport)
        boardingTime = findViewById(R.id.tv_boardingTime)
        departureTime = findViewById(R.id.tv_departureTime)
        arrivalTime = findViewById(R.id.tv_arrivalTime)
        departureDate = findViewById(R.id.tv_departureDate)
        gate = findViewById(R.id.tv_gateValue)
        terminal = findViewById(R.id.tv_terminalValue)
        seat = findViewById(R.id.tv_seatValue)
    }


    override fun displayBoardingData(viewModel: BoardingViewModel) {
//        Log.e(TAG, "displayBoardingData() called with: viewModel = [$viewModel]")
//        Log.e(TAG, "displayBoardingData() called with: flightModel = [$flightModel]")
        // Deal with the data
        val checkINModel = viewModel.checkINModel
        passengerName?.text = "Mohan Karats"
        arrivalCity?.text = flightModel?.arrivalCity
        arrivalTime?.text = flightModel?.arrivalTime
        departureCity?.text = flightModel?.departureCity
        departureTime?.text = flightModel?.departureTime
        departureDate?.text = flightModel?.startingTime

        gate?.text = checkINModel?.gate
        terminal?.text = checkINModel?.terminal
        seat?.text = checkINModel?.seat
    }

    companion object {
       const val TAG = "BoardingActivity"
    }
}
