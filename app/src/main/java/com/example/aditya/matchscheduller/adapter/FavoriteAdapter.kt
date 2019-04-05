package com.example.aditya.matchscheduller.adapter


import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.sqlite.Favourite
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter (private val favorite:List<Favourite>,private val listener: (Favourite) -> Unit)
    :RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.match,p0,false))
    }

    override fun getItemCount(): Int = favorite.size


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(favorite[p1],listener)
    }
}

class FavoriteViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val teamNameHome:TextView = view.findViewById(R.id.nameHome)
    private val teamNameAway:TextView = view.findViewById(R.id.nameAway)
    private val scoreHome:TextView = view.findViewById(R.id.scoreHome)
    private val scoreAway:TextView = view.findViewById(R.id.awayScore)
    private var date:TextView = view.find(R.id.date)
    private var time:TextView = view.find(R.id.time_last)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindItem(favourite: Favourite, listener: (Favourite) -> Unit ){
        teamNameHome.text = favourite.strHomeTeam
        teamNameAway.text = favourite.strAwayName
        scoreHome.text = favourite.intHomeScore
        scoreAway.text = favourite.intAwayScore
        date.text = favourite.dateEvent
        time.text = converTime(favourite.time.toString()).toString()
        itemView.setOnClickListener { listener(favourite) }

    }
    fun converTime(time:String):Date?{
        val format = SimpleDateFormat("HH:mm:ss")
        format.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$time"
        return format.parse(dateTime)
    }
}



