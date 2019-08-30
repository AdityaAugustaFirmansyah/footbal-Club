package com.example.aditya.matchscheduller.favorite


import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.detailmatch.AwayTeamDetail
import com.example.aditya.matchscheduller.detailmatch.homeTeamDetail
import com.example.aditya.matchscheduller.image.ImagePresenter
import com.example.aditya.matchscheduller.sqlite.Favourite
import com.example.aditya.matchscheduller.image.ImageViewMatch
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match.view.*
import org.jetbrains.anko.find
import java.time.format.DateTimeFormatter

class FavoriteAdapter (private val favorite:List<Favourite>,private val listener: (Favourite) -> Unit)
    :RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.match,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int = favorite.size


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(favorite[p1],listener)
    }
}

class FavoriteViewHolder (view: View): RecyclerView.ViewHolder(view),
    ImageViewMatch {
    override fun showBadgeHome(data: List<homeTeamDetail>) {
        Picasso.get().load(data[0].strTeamBadge).into(itemView.badge_home_list)
    }

    override fun showBadgeAway(data: List<AwayTeamDetail>) {
        Picasso.get().load(data[0].strTeamBadge).into(itemView.badge_away_list)
    }

    private val teamNameHome:TextView = view.findViewById(R.id.nameHome)
    private val teamNameAway:TextView = view.findViewById(R.id.nameAway)
    private val scoreHome:TextView = view.findViewById(R.id.scoreHome)
    private val scoreAway:TextView = view.findViewById(R.id.awayScore)
    private var date:TextView = view.find(R.id.date)
    private var time:TextView = view.find(R.id.time_last)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindItem(favourite: Favourite, listener: (Favourite) -> Unit ){
        val api = ApiRepositery()
        val gson = Gson()
        val presenter = ImagePresenter(this, gson, api)
        presenter.getHomeTeamBadge(favourite.idHome)
        presenter.getAwayTeamBadge(favourite.idAway)
        teamNameHome.text = favourite.strHomeTeam
        teamNameAway.text = favourite.strAwayName
        scoreHome.text = favourite.intHomeScore
        scoreAway.text = favourite.intAwayScore
        date.text = favourite.dateEvent
        time.text = converTime(favourite.time.toString())
        itemView.setOnClickListener { listener(favourite) }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun converTime(timeMatch:String?):String?{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS")
        val formated = timeMatch?.format(formatter)
        val result = formated?.substring(0,8)
        return result
    }
}



