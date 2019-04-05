package com.example.aditya.matchscheduller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.aditya.matchscheduller.adapter.FavoriteAdapter
import com.example.aditya.matchscheduller.sqlite.Favourite
import com.example.aditya.matchscheduller.sqlite.database
import com.example.aditya.matchscheduller.view.FavoriteMatchView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import utils.invisible
import utils.visible


class FavouriteFragment : Fragment(),FavoriteMatchView {

    private lateinit var progressBar: ProgressBar
    private lateinit var listFavorite:RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter:FavoriteAdapter
    private var favourites: MutableList<Favourite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_favourite, container, false)

        progressBar = rootView.findViewById(R.id.progressBarFavorite)
        listFavorite = rootView.findViewById(R.id.list_view_Favorite)
        swipeRefreshLayout = rootView.findViewById(R.id.refereshFavorite)


        listFavorite.layoutManager = LinearLayoutManager(activity)

        adapter = FavoriteAdapter(favourites){
            startActivity<DetailActivity>("idEvent" to it.idEvent,
                "idHome" to it.idHome,
                "idAway" to it.idAway,
                "date" to it.dateEvent,
                "time" to it.time)
        }

        listFavorite.adapter = adapter
        showFavorite()

        swipeRefreshLayout.onRefresh {
            favourites.clear()
            showFavorite()
        }


        return rootView
    }

    fun showFavorite(){
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(Favourite.TABLE_NAME)
            val favorites = result.parseList(classParser<Favourite>())
            favourites.addAll(favorites)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showLoadingFavorite() {
        progressBar.visible()
    }

    override fun hideLoadingFavorite() {
        progressBar.invisible()
    }

    companion object {
        fun newInstance():FavouriteFragment = FavouriteFragment()
    }
}
