package com.example.aditya.matchscheduller.player

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.PlayerResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerView,
    private val request: ApiRepositery,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayerList(idPlayer:String){
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                request
                    .doRequest(TheSportDBApi.getPlayer(idPlayer)).await(),
                PlayerResponse::class.java
            )

            view.hideLoading()
            view.showPlayer(data.players)
        }
    }
}