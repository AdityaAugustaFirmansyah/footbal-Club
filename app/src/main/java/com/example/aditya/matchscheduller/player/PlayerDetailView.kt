package com.example.aditya.matchscheduller.player

import com.example.aditya.matchscheduller.data.PlayerModel

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailPlayer(data:List<PlayerModel>)
}