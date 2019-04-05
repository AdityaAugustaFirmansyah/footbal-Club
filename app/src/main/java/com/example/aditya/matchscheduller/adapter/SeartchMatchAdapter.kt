package com.example.aditya.matchscheduller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.LastMatch
import com.example.aditya.matchscheduller.data.SearchModel
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class SeartchMatchAdapter(
    private val searchModel: MutableList<SearchModel>,
    private val listener: (SearchModel) -> Unit)
    :RecyclerView.Adapter<SearchViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.match,p0,false))
    }

    override fun getItemCount(): Int {
        return searchModel.size
    }

    override fun onBindViewHolder(p0: SearchViewHolder, p1: Int) {
        p0.bindItem(searchModel[0],listener)
    }
}

class SearchViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val home: TextView = view.findViewById(R.id.nameHome)
    private val away: TextView = view.findViewById(R.id.nameAway)
    private val homeScr: TextView = view.findViewById(R.id.scoreHome)
    private val awayScr: TextView = view.findViewById(R.id.awayScore)
    private val date: TextView = view.findViewById(R.id.date)
    private val time = view.find<TextView>(R.id.time_last)

    fun bindItem(lastMatch: SearchModel, listener:(SearchModel)->Unit){
        home.text =lastMatch.strHomeTeam
        away.text = lastMatch.strAwayTeam
        homeScr.text = lastMatch.intHomeScore
        awayScr.text = lastMatch.intAwayScore
        date.text = lastMatch.dateEvent
        time.text = converTime(lastMatch.time.toString()).toString()
        itemView.setOnClickListener {listener(lastMatch)}
    }

    fun converTime(time:String): Date?{
        val format = SimpleDateFormat("HH:mm:ss")
        format.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$time"
        return format.parse(dateTime)
    }
}
