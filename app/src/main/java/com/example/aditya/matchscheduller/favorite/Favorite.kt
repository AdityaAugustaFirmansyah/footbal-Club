package com.example.aditya.matchscheduller.favorite


import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aditya.matchscheduller.R
import kotlinx.android.synthetic.main.fragment_favorite.view.*


class Favorite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val fragmentAdapter = FavoritePagerAdapter(childFragmentManager)
        rootView.view_pager_favorite.adapter = fragmentAdapter
        rootView.tabs_favorite.setupWithViewPager(rootView.view_pager_favorite)

        return rootView
    }

    companion object {
        fun newInstace(): Favorite =
            Favorite()
    }
}
