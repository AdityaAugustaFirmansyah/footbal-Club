package com.example.aditya.matchscheduller.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.LastMatch
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class lastmatchHolder (view: View): RecyclerView.ViewHolder(view){
    private val home: TextView = view.findViewById(R.id.nameHome)
    private val away: TextView = view.findViewById(R.id.nameAway)
    private val homeScr: TextView = view.findViewById(R.id.scoreHome)
    private val awayScr: TextView = view.findViewById(R.id.awayScore)
    private val date: TextView = view.findViewById(R.id.date)
    private val time = view.find<TextView>(R.id.time_last)



    @RequiresApi(Build.VERSION_CODES.O)
    fun BindItem(lastMatch: LastMatch, listener:(LastMatch)->Unit){
        home.text =lastMatch.strHomeTeam
        away.text = lastMatch.strAwayTeam
        homeScr.text = lastMatch.intHomeScore
        awayScr.text = lastMatch.intAwayScore
        date.text = lastMatch.dateEvent
        time.text = converTime(lastMatch.time.toString()).toString()
        itemView.setOnClickListener {listener(lastMatch)}
    }

    fun converTime(time:String):Date?{
        val format = SimpleDateFormat("HH:mm:ss")
        format.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$time"
        return format.parse(dateTime)
    }
}