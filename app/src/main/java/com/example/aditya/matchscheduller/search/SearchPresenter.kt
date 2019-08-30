package com.example.aditya.matchscheduller.search

import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
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
            if(data.events!=null){
                view.showMatchSearch(data.events)
                view.hideLoading()
            }else{
                view.showError("Data Tidak Ditemukan")
                view.hideLoading()
            }

        }
    }

}