package com.example.aditya.matchscheduller.team

import com.example.aditya.matchscheduller.data.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}