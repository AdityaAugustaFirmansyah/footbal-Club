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
import com.example.aditya.matchscheduller.adapter.LastMatchAdapter
import com.example.aditya.matchscheduller.data.LastMatch
import com.example.aditya.matchscheduller.presenter.LastMatchPresenter
import com.example.aditya.matchscheduller.search.SearchMatch
import com.example.aditya.matchscheduller.view.LastMatchView
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import utils.invisible
import utils.visible

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LastMatchFragment : Fragment(),LastMatchView {

    private lateinit var processBar: ProgressBar
    private lateinit var list: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter:LastMatchAdapter
    private lateinit var presenter: LastMatchPresenter
    private var matches:MutableList<LastMatch> = mutableListOf()
    private lateinit var listLiga:Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView:View =    inflater.inflate(R.layout.fragment_last_match, container, false)

        processBar = rootView.find(R.id.progressBar)
        swipeRefresh = rootView.find(R.id.referesh)
        list = rootView.find(R.id.list_view)
        listLiga = rootView.find(R.id.list_liga)

        list.layoutManager = LinearLayoutManager(activity)
        adapter = LastMatchAdapter(matches){
            startActivity(intentFor<DetailActivity>("idEvent" to it.idEvent,
                "idHome" to it.idHomeTeam,
                "idAway" to it.idAwayTeam,
                "date" to it.dateEvent,
                "time" to it.time))
        }
        list.adapter = adapter


        val request = ApiRepositery()
        val gson = Gson()


        val league = arrayOf("4328","4329","4331","4332","4334","4335")
        val adapterSpinner = ArrayAdapter(activity,android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.league))

        val position = adapterSpinner.getPosition("English Premier League")

        presenter = LastMatchPresenter(this,gson,request)

        listLiga.adapter = adapterSpinner
        listLiga.setSelection(position)

        presenter.getLastMatch(league[0])

        listLiga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getLastMatch(league[position])
            }
        }
        swipeRefresh.onRefresh {
            presenter.getLastMatch(league[position])
        }


        return rootView
    }
    override fun showLoading(){
        processBar.visible()
    }

    override fun hideLoading() {
        processBar.invisible()
    }

    override fun showLastMatchList(data: List<LastMatch>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }



}

