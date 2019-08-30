package com.example.aditya.matchscheduller.image

import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.api.TheSportDBApi
import com.example.aditya.matchscheduller.CoroutineContextProvider
import com.example.aditya.matchscheduller.detailmatch.awayTeamResponse
import com.example.aditya.matchscheduller.detailmatch.homeTeamDetailResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImagePresenter(
    private val view: ImageViewMatch,
    private val gson: Gson,
    private val apiRepository: ApiRepositery,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getHomeTeamBadge(idTeam: String?) {

        GlobalScope.launch(context.main) {
            if (idTeam != null){
                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamBadge(idTeam)).await(),
                    homeTeamDetailResponse::class.java
                )
                view.showBadgeHome(data.homeTeamDetail)
            }
        }
    }


    fun getAwayTeamBadge(idTeam: String?) {
            if (idTeam != null){
                GlobalScope.launch(context.main) {
                    val data = gson.fromJson(
                        apiRepository
                            .doRequest(TheSportDBApi.getTeamBadge(idTeam)).await(),
                        awayTeamResponse::class.java
                    )
                view.showBadgeAway(data.awayTeamDetail)
            }
        }
    }
}