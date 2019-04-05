package com.example.aditya.matchscheduller.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aditya.matchscheduller.API.ApiRepositery

import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.adapter.PlayerAdapter
import com.example.aditya.matchscheduller.data.PlayerModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.fragment_player.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import utils.invisible
import utils.visible

class Player : Fragment(),PlayerView {

    private  var player:MutableList<PlayerModel> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_player, container, false)

        val args = arguments?.getString(id1)


        val request = ApiRepositery()
        val gson = Gson()

        adapter = PlayerAdapter(player){
            ctx.startActivity<PlayerDetail>("player" to "${it.playerId}")

        }
        presenter = PlayerPresenter(this,request,gson)
        rootView.list_player.layoutManager = LinearLayoutManager(activity)
        rootView.list_player.adapter = adapter

        presenter.getPlayerList(args!!)


        return rootView
    }

    companion object {
        const val id1 = "desc"
        fun newInstance(args: String): Player {
                val fragment = Player()
                val bundle = bundleOf(id1 to args)
                fragment.arguments = bundle
                return fragment
            }
    }

    override fun showLoading() {
        progress_player.visible()
    }

    override fun hideLoading() {
        progress_player.invisible()
    }

    override fun showPlayer(data: List<PlayerModel>) {

        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
