package com.example.aditya.matchscheduller.view

import com.example.aditya.matchscheduller.data.AwayTeamDetail
import com.example.aditya.matchscheduller.data.eventDetail
import com.example.aditya.matchscheduller.data.homeTeamDetail

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data:List<eventDetail>)
    fun showHomeTeamDetail(data: List<homeTeamDetail>)
    fun showAwayTeamDetail(data: List<AwayTeamDetail>)
}