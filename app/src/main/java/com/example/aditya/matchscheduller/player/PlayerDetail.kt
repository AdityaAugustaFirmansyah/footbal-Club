package com.example.aditya.matchscheduller.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.PlayerModel
import com.example.aditya.matchscheduller.team.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import utils.invisible
import utils.visible

class PlayerDetail : AppCompatActivity(),PlayerDetailView {

    private lateinit var id:String
    private lateinit var playerModel: PlayerModel
    private lateinit var presenter: PlayerDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        id = intent.getStringExtra("player")
        Log.d("ECEK",id)

        val request = ApiRepositery()
        val gson = Gson()

        presenter = PlayerDetailPresenter(this,request,gson)
        presenter.getPlayerDetail(id)
    }

    override fun showLoading() {
        progress_player_detail.visible()
    }

    override fun hideLoading() {
        progress_player_detail.invisible()
    }

    override fun showDetailPlayer(data: List<PlayerModel>) {
        playerModel = PlayerModel(data[0].playerId,
            data[0].playerName,
            data[0].playerPosition,
            data[0].playerThumb,data[0].playerPhoto,
            data[0].playerHeight,
            data[0].playerWeight,
            data[0].playerDesc)

        Picasso.get().load(playerModel.playerThumb).into(thumb_player)
        text_weight.text = playerModel.playerWeight
        text_height.text = playerModel.playerHeight
        text_position.text = playerModel.playerPosition
        text_desc_player.text = playerModel.playerDesc
    }

}
