package com.example.aditya.matchscheduller

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.example.aditya.matchscheduller.favorite.Favorite
import com.example.aditya.matchscheduller.league.ListLeagueFragment
import com.example.aditya.matchscheduller.team.TeamsFragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = find<BottomNavigationView>(R.id.Bottom_Navbar)
        if (checkNetwork(this)) {
            bottomNavigationView.setOnNavigationItemSelectedListener(mItemSelectedListener)
            bottomNavigationView.selectedItemId = R.id.match
        } else {
            alert("No Connection").show()
        }
        toolbar = supportActionBar!!

    }


    private val mItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.match -> {
                val leagueFragment = ListLeagueFragment.newInstance()
                openFragment(leagueFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.team -> {
                val teamFragmet = TeamsFragment.newInstance()
                openFragment(teamFragmet)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                val favouriteFragment = Favorite.newInstace()
                openFragment(favouriteFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun checkNetwork(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    private fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}