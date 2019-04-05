package com.example.aditya.matchscheduller.team

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class DetailPagerAdapter(fm:FragmentManager,val map: Map<String,Fragment>):FragmentStatePagerAdapter(fm) {
    val title = map.keys.toList()
    val fragment = map.values.toList()
    override fun getItem(p0: Int): Fragment {

        return fragment[p0]
    }

    override fun getCount(): Int {
        return map.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}