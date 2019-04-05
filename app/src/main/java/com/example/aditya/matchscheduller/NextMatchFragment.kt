package com.example.aditya.matchscheduller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.adapter.NextMatchAdapter
import com.example.aditya.matchscheduller.data.NextMatch
import com.example.aditya.matchscheduller.presenter.NextMatchPresenter
import com.example.aditya.matchscheduller.search.SearchMatch
import com.example.aditya.matchscheduller.view.NextMatchView
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import utils.invisible
import utils.visible
import java.util.zip.Inflater


class NextMatchFragment : Fragment(),NextMatchView {

    private lateinit var swipeRefers:SwipeRefreshLayout
    private var nextmatch:MutableList<NextMatch> = mutableListOf()
    private lateinit var listNext:RecyclerView
    private lateinit var nextMatchAdapter: NextMatchAdapter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var loading:ProgressBar
    private lateinit var listLiga:Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rooView:View =  inflater.inflate(R.layout.fragment_next__match, container, false)

        swipeRefers = rooView.find(R.id.refes)
        listNext = rooView.find(R.id.listNext)
        loading = rooView.find(R.id.progesBar)
        listLiga = rooView.find(R.id.list_liga_next)


        listNext.layoutManager=LinearLayoutManager(activity)
        nextMatchAdapter = NextMatchAdapter(nextmatch){
            startActivity(intentFor<DetailActivity>("idEvent" to it.idEvent,
                "idHome" to it.idHomeTeam,
                "idAway" to it.idAwayTeam,
                "date" to it.dateEvent,
                "time" to it.time))
        }
        listNext.adapter = nextMatchAdapter



        val request = ApiRepositery()
        val gson = Gson()

        val league = arrayOf("4328","4329","4331","4332","4334","4335")
        val adapterSpinner = ArrayAdapter(activity,android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.league))

        val position = adapterSpinner.getPosition("English Premier League")

        nextMatchPresenter = NextMatchPresenter(this,gson,request)
        listLiga.adapter = adapterSpinner
        listLiga.setSelection(position)

        nextMatchPresenter.getNextMatch(league[0])

        listLiga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nextMatchPresenter.getNextMatch(league[position])
            }
        }
        swipeRefers.onRefresh {
            nextMatchPresenter.getNextMatch(league[position])
        }


        return rooView
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.invisible()
    }

    override fun showNextMatchList(data: List<NextMatch>) {
        swipeRefers.isRefreshing = false
        nextmatch.clear()
        nextmatch.addAll(data)
        nextMatchAdapter.notifyDataSetChanged()
    }

}


