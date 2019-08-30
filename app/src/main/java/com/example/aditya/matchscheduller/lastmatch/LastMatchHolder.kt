package com.example.aditya.matchscheduller.lastmatch

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.detailmatch.AwayTeamDetail
import com.example.aditya.matchscheduller.detailmatch.homeTeamDetail
import com.example.aditya.matchscheduller.image.ImagePresenter
import com.example.aditya.matchscheduller.image.ImageViewMatch
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match.view.*
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class LastMatchHolder (view: View): RecyclerView.ViewHolder(view),
    ImageViewMatch {
    override fun showBadgeHome(data: List<homeTeamDetail>) {
        Picasso.get().load(data[0].strTeamBadge).into(itemView.badge_home_list)
    }

    override fun showBadgeAway(data: List<AwayTeamDetail>) {
        Picasso.get().load(data[0].strTeamBadge).into(itemView.badge_away_list)
    }

    private val home: TextView = view.findViewById(R.id.nameHome)
    private val away: TextView = view.findViewById(R.id.nameAway)
    private val homeScr: TextView = view.findViewById(R.id.scoreHome)
    private val awayScr: TextView = view.findViewById(R.id.awayScore)
    private val date: TextView = view.findViewById(R.id.date)
    private val time = view.find<TextView>(R.id.time_last)

    fun bindItem(lastMatch: LastMatch, listener:(LastMatch)->Unit){
        val api = ApiRepositery()
        val gson = Gson()
        val presenter = ImagePresenter(this, gson, api)
        presenter.getHomeTeamBadge(lastMatch.idHomeTeam)
        presenter.getAwayTeamBadge(lastMatch.idAwayTeam)
        home.text =lastMatch.strHomeTeam
        away.text = lastMatch.strAwayTeam
        homeScr.text = lastMatch.intHomeScore
        awayScr.text = lastMatch.intAwayScore
        date.text = convertDate(lastMatch.time,lastMatch.dateEvent!!)
        time.text = convertTime(lastMatch.time)
        itemView.setOnClickListener {listener(lastMatch)}
    }

    private fun convertTime(timeMatch:String?):String?{
        val formatter = SimpleDateFormat("HH:mm:SS",Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        return formatter.parse(timeMatch).toString().substring(10,16)
    }

    private fun convertDate(timeMatch:String?, dateMatch:String):String{
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:SS",Locale.getDefault())
        formater.timeZone = TimeZone.getTimeZone("GMT")
        return formater.parse("$dateMatch $timeMatch").toString().substring(0,10)
    }
}