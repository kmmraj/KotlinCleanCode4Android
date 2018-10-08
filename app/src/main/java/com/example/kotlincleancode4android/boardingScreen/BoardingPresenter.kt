package com.example.kotlincleancode4android.boardingScreen

import java.lang.ref.WeakReference

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
interface BoardingPresenterInput {
    fun presentBoardingData(response: BoardingResponse)
}

class BoardingPresenter : BoardingPresenterInput {

    var output: WeakReference<BoardingActivityInput>? = null

    override fun presentBoardingData(response: BoardingResponse) {
        // Log.e(TAG, "presentBoardingData() called with: response = [" + response + "]");
        // Do your decoration or filtering here
        // Model and Viewmodel is same here
        val boardingViewModel = BoardingViewModel()
        boardingViewModel.checkINModel = response.checkINModel
        output?.get()?.displayBoardingData(boardingViewModel)
    }

    companion object {
        const val TAG = "BoardingPresenter"
    }
}