package com.example.aditya.matchscheduller.team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
    fun showError(error:String)
}