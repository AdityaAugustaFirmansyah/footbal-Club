package com.example.aditya.matchscheduller.player

import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailPresenter(
    private val view: PlayerDetailView,
    private val request :ApiRepositery,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()

){
    fun getPlayerDetail(playerId: String) {
        view.showLoading()

        GlobalScope.launch(contextProvider.main){
            val data = gson.fromJson(request
                .doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                PlayerDetailResponse::class.java
            )

            view.showDetailPlayer(data.players)
            view.hideLoading()
        }
    }
}