package com.example.aditya.matchscheduller.search

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.detailmatch.DetailActivity
import com.example.aditya.matchscheduller.lastmatch.LastMatch
import com.example.aditya.matchscheduller.lastmatch.LastMatchAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import utils.invisible
import utils.visible

class SearchMatch : AppCompatActivity(),SearchViewMatch {
    override fun showError(error: String) {
        swipe_refresh_search_match.snackbar(error)
    }

    private lateinit var presenter: SearchPresenter
    private var lastMatch:MutableList<LastMatch> = mutableListOf()
    private lateinit var listDataNext:RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchadapter: LastMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        progressBar = find(R.id.progress_search)
        listDataNext = find(R.id.list_search)

        listDataNext = find(R.id.list_search)

        searchadapter= LastMatchAdapter(lastMatch) {
            startActivity<DetailActivity>(
                "idEvent" to it.idEvent,
                "idHome" to it.idHomeTeam,
                "idAway" to it.idAwayTeam,
                "date" to it.dateEvent,
                "time" to it.time
            )
        }

        with(listDataNext){
            adapter = searchadapter
            listDataNext.layoutManager = LinearLayoutManager(context)
        }

        val keySearch = intent.getStringExtra("keySearch")
        val gson = Gson()
        val requset = ApiRepositery()
        if(checkNetwork(this)){
            presenter = SearchPresenter(this,requset,gson)
            presenter.getDataSearchMatch(keySearch)

            swipe_refresh_search_match.onRefresh {
                presenter.getDataSearchMatch(keySearch)
            }
        }else{
         alert("no Connection").show()
        }
    }



    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchSearch( dataNext: MutableList<LastMatch>) {
        lastMatch.clear()
        lastMatch.addAll(dataNext)
        searchadapter.notifyDataSetChanged()
        listDataNext.scrollToPosition(0)
    }
    private fun checkNetwork(context: Context?):Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}
