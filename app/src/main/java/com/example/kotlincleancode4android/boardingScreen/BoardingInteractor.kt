package com.example.kotlincleancode4android.boardingScreen

/**
 * Created by Mohanraj Karatadipalayam on 08/10/18.
 */
interface BoardingInteractorInput {
    fun fetchBoardingData(request: BoardingRequest)
}

class BoardingInteractor: BoardingInteractorInput {

    var output: BoardingPresenterInput? = null
    private var internalBoardingWorkerInput: BoardingWorkerInput? = null

    private var boardingWorkerInput: BoardingWorkerInput
        get() = if (internalBoardingWorkerInput == null) BoardingWorker()
        else
            internalBoardingWorkerInput!!
        set(boardingWorkerInput) {
            this.internalBoardingWorkerInput = boardingWorkerInput
        }

    override fun fetchBoardingData(request: BoardingRequest) {
        internalBoardingWorkerInput = boardingWorkerInput
        val aBoardingResponse = BoardingResponse()
        // Call the workers
        aBoardingResponse.checkINModel = internalBoardingWorkerInput?.getCheckINDetails(request)
        output?.presentBoardingData(aBoardingResponse)
    }

    companion object {
        const val TAG = "BoardingInteractor"
    }
}