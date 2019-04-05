package com.example.aditya.matchscheduller.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.NextMatch
import com.example.aditya.matchscheduller.data.SearchModel

class NextMatchAdapter(
    private val nextMatch: List<NextMatch>,
    private val listener: (NextMatch) -> Unit)
    : RecyclerView.Adapter<nextViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = nextViewHolder(LayoutInflater.from(p0.context)
        .inflate(R.layout.match,p0,false))

    override fun getItemCount(): Int = nextMatch.size

    override fun onBindViewHolder(p0: nextViewHolder, p1: Int) {
        p0.BindItem(nextMatch[p1],listener)
    }
}

