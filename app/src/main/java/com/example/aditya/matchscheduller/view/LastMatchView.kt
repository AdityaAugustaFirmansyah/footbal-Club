package com.example.aditya.matchscheduller.view

import com.example.aditya.matchscheduller.data.LastMatch
import com.example.aditya.matchscheduller.data.eventDetail

interface LastMatchView{
    fun hideLoading()
    fun showLoading()
    fun showLastMatchList(data: List<LastMatch>)
}
