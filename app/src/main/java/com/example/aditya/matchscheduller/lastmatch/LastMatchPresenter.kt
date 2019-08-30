package com.example.aditya.matchscheduller.lastmatch

import android.util.Log
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            Log.d("INIDATA",data.toString())
            if(data.lastMatch == null){
                view.showErroe("gagal")
            }else{
                view.showLastMatchList(data.lastMatch)
            }
            view.hideLoading()
        }
    }

}

