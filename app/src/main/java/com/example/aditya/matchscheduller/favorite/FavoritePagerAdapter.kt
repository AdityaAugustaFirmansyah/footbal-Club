package com.example.aditya.matchscheduller.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.aditya.matchscheduller.FavouriteFragment
import com.example.aditya.matchscheduller.team.FavoriteTeam

class FavoritePagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getItem(p0: Int): Fragment {
        return when (p0){
            0->FavouriteFragment()
            else -> FavoriteTeam()
        }
    }

    override fun getCount(): Int = 2


    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Match Favorite"
            else -> "Team Favorite"
        }
    }
}