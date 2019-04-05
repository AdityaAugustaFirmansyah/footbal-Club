package com.example.aditya.matchscheduller.presenter

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.NextMatchResponse
import com.example.aditya.matchscheduller.view.NextMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NextMatchPresenter(
    private val view: NextMatchView,
    private val gson: Gson,
    private val apiRepositery:ApiRepositery,
    private val context:CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getNextMatch(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepositery
                    .doRequest(TheSportDBApi.getNextMatch(league)).await(),
                NextMatchResponse::class.java
            )


            view.hideLoading()
            view.showNextMatchList(data.ListMatch)
        }
    }
}
