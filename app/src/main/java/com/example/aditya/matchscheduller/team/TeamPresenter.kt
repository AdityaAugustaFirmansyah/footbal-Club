package com.example.aditya.matchscheduller.team

import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
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
            if (data==null){
                view.showError("Data Team Gagal Di Tampilkan")
            }else{
                view.showTeamList(data.teams)
            }

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
                if (data.teams == null){
                    view.showError("Data Tidak Ditemukan")
                }else{
                    view.showTeamList(data.teams)
                }
            }
        }
    }
}