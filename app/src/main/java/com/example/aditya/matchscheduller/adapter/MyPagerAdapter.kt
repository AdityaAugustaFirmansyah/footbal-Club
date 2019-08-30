package com.example.aditya.matchscheduller.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPagerAdapter(fragmentManager: FragmentManager,val map: Map<String,Fragment>):FragmentPagerAdapter(fragmentManager) {
    val tiitle = map.keys.toList()
    val fragment = map.values.toList()
    override fun getItem(p0: Int): Fragment {
        return fragment[p0]
    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tiitle[position]
    }
}