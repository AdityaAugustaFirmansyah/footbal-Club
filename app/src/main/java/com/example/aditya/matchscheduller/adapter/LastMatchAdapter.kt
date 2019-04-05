package com.example.aditya.matchscheduller.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aditya.matchscheduller.data.LastMatch
import com.example.aditya.matchscheduller.R


class LastMatchAdapter(
    private val lastMatch: List<LastMatch>,
    private val listener: (LastMatch) -> Unit)
    : RecyclerView.Adapter<lastmatchHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)= lastmatchHolder(LayoutInflater.from(p0.context)
        .inflate(R.layout.match,p0,false))

    override fun getItemCount(): Int = lastMatch.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(p0: lastmatchHolder, p1: Int) {
        p0.BindItem(lastMatch[p1],listener)
    }

}

