package com.example.aditya.matchscheduller.team

import com.example.aditya.matchscheduller.data.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}