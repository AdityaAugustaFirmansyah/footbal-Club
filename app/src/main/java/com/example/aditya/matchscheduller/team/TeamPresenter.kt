package com.example.aditya.matchscheduller.team

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepositery,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

    fun getSearchTeam(query: String = "") {
        view.showLoading()

        GlobalScope.launch {
            GlobalScope.launch(context.main) {
                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDataSearchTeam(query)).await(),
                    TeamResponse::class.java
                )

                view.hideLoading()
                view.showTeamList(data.teams)

            }
        }
    }
}