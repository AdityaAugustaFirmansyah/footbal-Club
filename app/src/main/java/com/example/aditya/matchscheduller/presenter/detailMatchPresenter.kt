package com.example.aditya.matchscheduller.presenter

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.awayTeamResponse
import com.example.aditya.matchscheduller.data.evenDetailResponse
import com.example.aditya.matchscheduller.data.homeTeamDetailResponse
import com.example.aditya.matchscheduller.view.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class detailMatchPresenter(
    private val view: DetailMatchView,
    private val gson: Gson,
    private val api:ApiRepositery,
    private val context:CoroutineContextProvider = CoroutineContextProvider()
    ) {

    fun getMatchDetails(idEvent: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api
                    .doRequest(TheSportDBApi.getEvenDetail(idEvent)).await(),
                evenDetailResponse::class.java
            )

            view.hideLoading()
            view.showEventDetail(data.eventDetail)
        }
    }


    fun getHomeTeamBadge(idTeam: String?) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api
                    .doRequest(TheSportDBApi.getTeamBadge(idTeam)).await(),
                homeTeamDetailResponse::class.java
            )

            view.showHomeTeamDetail(data.homeTeamDetail)
        }
    }


    fun getAwayTeamBadge(idTeam: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                api
                    .doRequest(TheSportDBApi.getTeamBadge(idTeam)).await(),
                awayTeamResponse::class.java
            )

            view.showAwayTeamDetail(data.awayTeamDetail)
        }
    }
}
