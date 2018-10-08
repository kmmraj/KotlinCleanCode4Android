package com.example.kotlincleancode4android.boardingScreen

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
data class CheckINModel(
        var flightName: String? = null,
        var startingTime: String? = null,
        var terminal: String? = null,
        var gate: String? = null,
        var seat: String? = null
)

// filter to have only the needed data
data class BoardingViewModel(var checkINModel: CheckINModel? = null)

data class BoardingRequest(var ffNumber: String? = null)

data class BoardingResponse(var checkINModel: CheckINModel? = null)
