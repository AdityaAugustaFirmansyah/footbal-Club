package com.example.aditya.matchscheduller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.PlayerModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerAdapter(val player:List<PlayerModel>,val listener:(PlayerModel)->Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_player,p0,false))
    }

    override fun getItemCount(): Int {
        return player.size
    }

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bindItem(player[p1],listener)
    }
}





class PlayerViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val playerImage = view.find<ImageView>(R.id.image_player)
    private val playerName = view.find<TextView>(R.id.text_name_player)
    private val playerPosition = view.find<TextView>(R.id.text_position)

    fun bindItem(playerModel: PlayerModel,listener: (PlayerModel)->Unit){
        Picasso.get() .load(playerModel.playerPhoto) .into(playerImage)
        playerName.text = playerModel.playerName
        playerPosition.text = playerModel.playerPosition

        itemView.setOnClickListener { listener(playerModel) }
    }
}
