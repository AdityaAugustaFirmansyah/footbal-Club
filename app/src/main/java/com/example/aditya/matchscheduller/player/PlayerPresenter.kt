package com.example.aditya.matchscheduller.player

import android.util.Log
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
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
            Log.d("INIDATA",data.toString())
            if (data.players == null){
                view.showError("Gagal")
            }else{
                view.showPlayer(data.players)
            }
            view.hideLoading()
        }
    }
}