package com.example.aditya.matchscheduller.lastmatch

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.detailmatch.DetailActivity
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast
import utils.invisible
import utils.visible

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LastMatchFragment : Fragment(), LastMatchView {

    override fun showErroe(error: String) {
        swipeRefresh.isRefreshing = false
        toast(error)
    }

    private lateinit var processBar: ProgressBar
    private lateinit var list: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: LastMatchAdapter
    private lateinit var presenter: LastMatchPresenter
    private var matches:MutableList<LastMatch> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView:View =    inflater.inflate(R.layout.fragment_last_match, container, false)

        processBar = rootView.find(R.id.progressBar)
        swipeRefresh = rootView.find(R.id.referesh)
        list = rootView.find(R.id.list_view)

        val request = ApiRepositery()
        val gson = Gson()

        list.layoutManager = LinearLayoutManager(activity)
        adapter = LastMatchAdapter(matches) {
            startActivity(
                intentFor<DetailActivity>(
                    "idEvent" to it.idEvent,
                    "idHome" to it.idHomeTeam,
                    "idAway" to it.idAwayTeam,
                    "date" to it.dateEvent,
                    "time" to it.time
                )
            )
        }
        list.adapter = adapter

        val idLeague = arguments?.getString(idLeague)

        if(isNetworkConnceted(context)){
            presenter = LastMatchPresenter(this, gson, request)
            presenter.getLastMatch(idLeague)
            swipeRefresh.onRefresh {
                presenter.getLastMatch(idLeague)
            }
        }else{
            alert("No Connection").show()
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

    fun isNetworkConnceted(context: Context?):Boolean{
        val connectivityManager:ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return  connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    companion object {
        const val idLeague = "idLeague"
        fun newInstance(args:String):Fragment{
            val fragment = LastMatchFragment()
            val bundle:Bundle = bundleOf(idLeague to  args)
            fragment.arguments = bundle
            return fragment
        }
    }
}

