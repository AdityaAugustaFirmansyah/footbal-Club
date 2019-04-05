package com.example.aditya.matchscheduller.player

import com.example.aditya.matchscheduller.data.PlayerModel

interface PlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data:List<PlayerModel>)
}