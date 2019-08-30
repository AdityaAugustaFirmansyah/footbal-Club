package com.example.aditya.matchscheduller.nextmatch


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
import com.example.aditya.matchscheduller.detailmatch.DetailActivity
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast
import utils.invisible
import utils.visible


class NextMatchFragment : Fragment(), NextMatchView {
    override fun showError(error: String) {
        swipeRefers.isRefreshing = false
        toast(error)
    }

    private lateinit var swipeRefers:SwipeRefreshLayout
    private var nextmatch:MutableList<NextMatch> = mutableListOf()
    private lateinit var listNext:RecyclerView
    private lateinit var nextMatchAdapter: NextMatchAdapter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var loading:ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rooView:View =  inflater.inflate(R.layout.fragment_next__match, container, false)

        swipeRefers = rooView.find(R.id.refes)
        listNext = rooView.find(R.id.listNext)
        loading = rooView.find(R.id.progesBar)

        val idLeague = arguments?.getString("idMatch")

        listNext.layoutManager=LinearLayoutManager(activity)
        nextMatchAdapter = NextMatchAdapter(nextmatch) {
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
        listNext.adapter = nextMatchAdapter

        val request = ApiRepositery()
        val gson = Gson()

            if (checkNetwork(context)){

                nextMatchPresenter =
                    NextMatchPresenter(this, gson, request)
                nextMatchPresenter.getNextMatch(idLeague)

                swipeRefers.onRefresh {
                    nextMatchPresenter.getNextMatch(idLeague)
                }
            }else{
                alert("No Connection").show()
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

    fun checkNetwork(context:Context?):Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    companion object {
        fun newInstance(idLeague:String):Fragment{
            val args = bundleOf("idMatch" to idLeague)
            val fragment = NextMatchFragment()
            fragment.arguments = args
            return fragment
        }
    }

}


