package com.example.aditya.matchscheduller.image

import com.example.aditya.matchscheduller.detailmatch.AwayTeamDetail
import com.example.aditya.matchscheduller.detailmatch.homeTeamDetail

interface ImageViewMatch {
    fun showBadgeHome(data:List<homeTeamDetail>)
    fun showBadgeAway(data:List<AwayTeamDetail>)
}