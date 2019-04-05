package com.example.aditya.matchscheduller.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.ProgressBar
import android.widget.SearchView
import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.DetailActivity
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.adapter.LastMatchAdapter
import com.example.aditya.matchscheduller.data.LastMatch
import com.google.gson.Gson
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import utils.invisible
import utils.visible

class SearchMatch : AppCompatActivity(),SearchViewMatch {

    private lateinit var presenter: SearchPresenter
    private var nextMatch:MutableList<LastMatch> = mutableListOf()
    private lateinit var listDataNext:RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView
    private lateinit var searchadapter: LastMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        progressBar = find(R.id.progress_search)
        listDataNext = find(R.id.list_search)

        listDataNext = find(R.id.list_search)

        searchadapter= LastMatchAdapter(nextMatch){
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
            listDataNext.layoutManager = LinearLayoutManager(ctx)
        }


        val gson = Gson()
        val requset = ApiRepositery()

        presenter = SearchPresenter(this,requset,gson)

        presenter.getDataSearchMatch()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search,menu)
        val searchItem = menu?.findItem(R.id.search_match)

        searchView = searchItem?.actionView as SearchView
        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                 presenter.getDataSearchMatch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                    presenter.getDataSearchMatch(newText.toString())

                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchSearch( dataNext: MutableList<LastMatch>) {
        nextMatch.clear()
        nextMatch.addAll(dataNext)
        searchadapter.notifyDataSetChanged()
        listDataNext.scrollToPosition(0)
    }
}
