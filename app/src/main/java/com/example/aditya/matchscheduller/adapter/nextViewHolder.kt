package com.example.aditya.matchscheduller.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.NextMatch
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class nextViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val home: TextView = view.findViewById(R.id.nameHome)
    private val away: TextView = view.findViewById(R.id.nameAway)
    private val date: TextView = view.findViewById(R.id.date)
    private var time = view.find<TextView>(R.id.time_last)


    @RequiresApi(Build.VERSION_CODES.O)
    fun BindItem(teammatch: NextMatch, listener:(NextMatch)->Unit){
        home.text = teammatch.strHomeTeam
        away.text = teammatch.strAwayTeam
        date.text = teammatch.dateEvent
        time.text = converTime(teammatch.time.toString()).toString()
        itemView.setOnClickListener { listener(teammatch) }
    }

    @SuppressLint("SimpleDateFormat")
    fun converTime(time:String):Date?{
        val format = SimpleDateFormat("HH:mm:ss")
        format.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$time"
        return format.parse(dateTime)
    }
}