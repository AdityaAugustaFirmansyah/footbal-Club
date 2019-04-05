package com.example.aditya.matchscheduller.search

import com.example.aditya.matchscheduller.data.LastMatch
import com.example.aditya.matchscheduller.data.NextMatch
import com.example.aditya.matchscheduller.data.SearchModel

interface SearchViewMatch {
    fun showLoading()
    fun hideLoading()
    fun showMatchSearch(dataNext: MutableList<LastMatch>)
}