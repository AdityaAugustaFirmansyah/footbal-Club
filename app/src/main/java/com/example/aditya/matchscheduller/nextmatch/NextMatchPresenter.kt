package com.example.aditya.matchscheduller.nextmatch

import android.util.Log
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
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
                if (data.ListMatch == null){
                    view.showError("Pertandinagan selanjutnya gagal di tampilkan")
                }else {
                    view.showNextMatchList(data.ListMatch)
                    Log.d("INITAG1", data.toString())
                }
            }
        view.hideLoading()
    }
}
