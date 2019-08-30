package com.example.aditya.matchscheduller.league

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.example.aditya.matchscheduller.lastmatch.LastMatchFragment
import com.example.aditya.matchscheduller.nextmatch.NextMatchFragment
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.adapter.MyPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    @SuppressLint("PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppThemeDetailLeague)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_detail_league)

        Picasso.get().load(intent.getStringExtra("imageLeague")).into(badge_league_detail)

        collapsing_toolbar_league_detail.title = intent.getStringExtra("nameLeague")
        collapsing_toolbar_league_detail.setCollapsedTitleTextColor(ContextCompat.getColor(this,android.R.color.white))
        collapsing_toolbar_league_detail.setExpandedTitleColor(ContextCompat.getColor(this,R.color.mtrl_btn_transparent_bg_color))


        val fragmentAdapter = MyPagerAdapter(supportFragmentManager, mapOf(
            "Last Match" to LastMatchFragment.newInstance(intent.getStringExtra("leagueId")),
            "Next Match" to NextMatchFragment.newInstance(intent.getStringExtra("leagueId"))
        ))

        view_pager_detail_league.adapter = fragmentAdapter
        tab_layout_detail_league.setupWithViewPager(view_pager_detail_league)
    }
}
