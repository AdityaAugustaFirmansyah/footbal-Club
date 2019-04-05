package com.example.aditya.matchscheduller.team

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.data.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepositery,
                          private val gson: Gson,
                          private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )

            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }
}