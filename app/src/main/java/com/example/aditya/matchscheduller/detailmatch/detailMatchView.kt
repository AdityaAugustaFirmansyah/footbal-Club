package com.example.aditya.matchscheduller.detailmatch

import com.example.aditya.matchscheduller.detailmatch.AwayTeamDetail
import com.example.aditya.matchscheduller.detailmatch.eventDetail
import com.example.aditya.matchscheduller.detailmatch.homeTeamDetail

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data:List<eventDetail>)
    fun showHomeTeamDetail(data: List<homeTeamDetail>)
    fun showAwayTeamDetail(data: List<AwayTeamDetail>)
}