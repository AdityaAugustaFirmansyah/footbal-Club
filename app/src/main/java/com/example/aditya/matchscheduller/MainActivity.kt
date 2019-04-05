package com.example.aditya.matchscheduller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.find
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.SearchEvent
import android.view.View
import android.widget.Switch
import android.widget.Toolbar
import com.example.aditya.matchscheduller.data.Team
import com.example.aditya.matchscheduller.favorite.Favorite
import com.example.aditya.matchscheduller.search.SearchMatch
import com.example.aditya.matchscheduller.team.TeamsFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.sdk27.coroutines.__SearchView_OnQueryTextListener
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
private lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        val bottomNavigationView = find<BottomNavigationView>(R.id.Bottom_Navbar)

        bottomNavigationView.setOnNavigationItemSelectedListener(mItemSelectedListener)
        bottomNavigationView.selectedItemId = R.id.match

        toolbar = supportActionBar!!
    }



    private val mItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.match -> {
                val matchFragment = Match.newInstance()
                openFragment(matchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.team -> {
                val teamFragmet = TeamsFragment.newInstance()
                openFragment(teamFragmet)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite ->{
                val favouriteFragment = Favorite.newInstace()
                openFragment(favouriteFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}