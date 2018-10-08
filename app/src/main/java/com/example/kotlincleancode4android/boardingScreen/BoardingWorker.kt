package com.example.kotlincleancode4android.boardingScreen

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
interface BoardingWorkerInput {
    // Define needed interfaces
    fun getCheckINDetails(boardingRequest: BoardingRequest): CheckINModel
}

class BoardingWorker: BoardingWorkerInput {

    override fun getCheckINDetails(boardingRequest: BoardingRequest): CheckINModel {
        val checkINModel = CheckINModel()
        checkINModel.flightName = ""
        checkINModel.startingTime = ""
        checkINModel.gate = "24"
        checkINModel.terminal = "2"
        checkINModel.seat = "6A"
        return checkINModel
    }
}