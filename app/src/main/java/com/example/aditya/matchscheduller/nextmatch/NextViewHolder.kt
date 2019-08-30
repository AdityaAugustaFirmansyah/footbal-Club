package com.example.aditya.matchscheduller.nextmatch

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

class NextViewHolder(view: View): RecyclerView.ViewHolder(view),
    ImageViewMatch {
    override fun showBadgeHome(data: List<homeTeamDetail>) {
        Picasso.get().load(data[0].strTeamBadge).into(itemView.badge_home_list)
    }

    override fun showBadgeAway(data: List<AwayTeamDetail>) {
        Picasso.get().load(data[0].strTeamBadge).into(itemView.badge_away_list)
    }

    private val home: TextView = view.findViewById(R.id.nameHome)
    private val away: TextView = view.findViewById(R.id.nameAway)
    private val date: TextView = view.findViewById(R.id.date)
    private var time = view.find<TextView>(R.id.time_last)

    fun bindItem(teamMatch: NextMatch, listener:(NextMatch)->Unit){
        val api = ApiRepositery()
        val gson = Gson()
        val presenter = ImagePresenter(this, gson, api)
        presenter.getHomeTeamBadge(teamMatch.idHomeTeam)
        presenter.getAwayTeamBadge(teamMatch.idAwayTeam)
        home.text = teamMatch.strHomeTeam
        away.text = teamMatch.strAwayTeam
        date.text = convertDate(teamMatch.time,teamMatch.dateEvent!!)
        time.text = converTime(teamMatch.time)
        itemView.setOnClickListener { listener(teamMatch) }
    }

    private fun converTime(timeMatch:String?):String?{
        val formatter = SimpleDateFormat("HH:mm:SS", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val dateMatch = formatter.parse("$timeMatch")
        return dateMatch.toString().substring(10,16)
    }

    private fun convertDate(timeMatch:String?, date:String?):String{
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val dateMatch = formatter.parse("$date $timeMatch")
        return dateMatch.toString().substring(0,10)
    }
}