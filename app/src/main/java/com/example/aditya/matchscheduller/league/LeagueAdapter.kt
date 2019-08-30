package com.example.aditya.matchscheduller.league

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aditya.matchscheduller.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_league.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class LeagueAdapter(var listLeague:ArrayList<League>, val context: Context) : RecyclerView.Adapter<LeagueViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LeagueViewHolder {
        val leagueView:View = LayoutInflater.from(context).inflate(R.layout.list_league,p0,false)
        return LeagueViewHolder(leagueView,context)
    }

    override fun getItemCount(): Int = listLeague.size

    override fun onBindViewHolder(p0: LeagueViewHolder, p1: Int) {
        p0.bindItem(listLeague[p1])
    }
}

class LeagueViewHolder(view: View,val context:Context) : RecyclerView.ViewHolder(view){
    fun bindItem(league:League){
        itemView.tv_league.text = league.nameLeague
        Picasso.get().load(league.imageLeague).into(itemView.image_league)
        itemView.card_league.onClick {
            context.startActivity<DetailLeagueActivity>("leagueId" to league.idLeague,
                "imageLeague" to league.imageLeague,"nameLeague" to league.nameLeague)
        }
    }
}