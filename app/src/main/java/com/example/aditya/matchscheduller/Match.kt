package com.example.aditya.matchscheduller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.example.aditya.matchscheduller.adapter.MyPagerAdapter
import com.example.aditya.matchscheduller.lastmatch.LastMatchFragment
import com.example.aditya.matchscheduller.nextmatch.NextMatchFragment
import kotlinx.android.synthetic.main.fragment_match.view.*


class Match : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)
        val fragmenAdapter = MyPagerAdapter(childFragmentManager, mapOf(
            "LastMatch" to LastMatchFragment(),
            "NextMatch" to NextMatchFragment()
        ))
        rootView.viewpager_main.adapter = fragmenAdapter

        rootView.tabs_main.setupWithViewPager(rootView.viewpager_main)

        return rootView
    }

    companion object {

        fun newInstance(): Match = Match()
    }
}