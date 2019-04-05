package com.example.aditya.matchscheduller.presenter

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.LastMatchResponse
import com.example.aditya.matchscheduller.view.LastMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(
    private val view: LastMatchView,
    private val gson:Gson,
    private val apiRepository:ApiRepositery,
    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLastMatch(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLastMatches(league)).await(),
                LastMatchResponse::class.java
            )

            view.hideLoading()
            view.showLastMatchList(data.lastMatch)
        }
    }
}

