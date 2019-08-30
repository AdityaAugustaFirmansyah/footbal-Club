package com.example.aditya.matchscheduller.search

import com.example.aditya.matchscheduller.lastmatch.LastMatch

interface SearchViewMatch {
    fun showLoading()
    fun hideLoading()
    fun showMatchSearch(dataNext: MutableList<LastMatch>)
    fun showError(error:String)
}