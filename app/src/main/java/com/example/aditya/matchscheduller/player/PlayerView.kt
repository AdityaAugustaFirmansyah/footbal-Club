package com.example.aditya.matchscheduller.player

interface PlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data:List<PlayerModel>)
    fun showError(error: String)
}