package com.example.aditya.matchscheduller.player

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailPlayer(data:List<PlayerModel>?)
}