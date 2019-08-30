package com.example.aditya.matchscheduller.nextmatch

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aditya.matchscheduller.R

class NextMatchAdapter(
    private val nextMatch: List<NextMatch>,
    private val listener: (NextMatch) -> Unit)
    : RecyclerView.Adapter<NextViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        NextViewHolder(
            LayoutInflater.from(p0.context)
                .inflate(R.layout.match, p0, false)
        )

    override fun getItemCount(): Int = nextMatch.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(p0: NextViewHolder, p1: Int) {
        p0.bindItem(nextMatch[p1],listener)
    }
}

