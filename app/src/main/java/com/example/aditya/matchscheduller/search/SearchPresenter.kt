package com.example.aditya.matchscheduller.search

import android.util.Log
import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.SearchNextMatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(
    val view: SearchViewMatch,
    val request:ApiRepositery,
    val gson: Gson,
    val coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider()
)
{
    fun getDataSearchMatch(league: String ="") {
        view.showLoading()

        GlobalScope.launch(coroutineContextProvider.main) {
            val data = gson.fromJson(
                request
                    .doRequest(TheSportDBApi.getDataSearchMatch(league)).await(),
                SearchNextMatchResponse::class.java
            )

            view.hideLoading()
            view.showMatchSearch(data.events)
        }
    }

}