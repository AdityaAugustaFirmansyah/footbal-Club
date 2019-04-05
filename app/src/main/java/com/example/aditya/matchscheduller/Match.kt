package com.example.aditya.matchscheduller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.example.aditya.matchscheduller.adapter.MyPagerAdapter
import com.example.aditya.matchscheduller.search.SearchMatch
import kotlinx.android.synthetic.main.fragment_match.view.*
import org.jetbrains.anko.startActivity


class Match : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)

        setHasOptionsMenu(true)


        val fragmenAdapter = MyPagerAdapter(childFragmentManager)
        rootView.viewpager_main.adapter = fragmenAdapter

        rootView.tabs_main.setupWithViewPager(rootView.viewpager_main)

        return rootView
    }

    companion object {

        fun newInstance(): Match = Match()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_search_view, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

     return   when (item?.itemId) {
            R.id.thumb_search -> {
                context?.startActivity<SearchMatch>()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}