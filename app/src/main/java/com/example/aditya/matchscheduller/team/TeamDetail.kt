package com.example.aditya.matchscheduller.team

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.R.menu.detail_menu
import com.example.aditya.matchscheduller.data.Team
import com.example.aditya.matchscheduller.player.Player
import com.example.aditya.matchscheduller.sqlite.TeamFavorite
import com.example.aditya.matchscheduller.sqlite.databaseTeam
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import utils.invisible
import utils.visible

class TeamDetail : AppCompatActivity(), TeamDetailView  {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var progressBar: ProgressBar

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id:String
    private lateinit var desc:String
    private lateinit var name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val intent = intent

        id = intent.getStringExtra("id")
        desc = intent.getStringExtra("desc")
        name = intent.getStringExtra("name")

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        teamBadge = find(R.id.ic_club)
        teamName = find(R.id.text_name)
        teamFormedYear = find(R.id.text_since)
        teamStadium = find(R.id.text_stadium)
        progressBar = find(R.id.progress_detail)

        val fragmentAdapter = DetailPagerAdapter(supportFragmentManager, mapOf(
            "Description" to Description.newInstance(desc),
            "player" to Player.newInstance(name)
        ))
        view_pager.adapter = fragmentAdapter
        tab_layout.setupWithViewPager(view_pager)
        val request = ApiRepositery()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        favoriteState()

    }



    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium

    }

    private fun favoriteState(){
        databaseTeam.use {
            val result = select(TeamFavorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<TeamFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    companion object {
        const val teams1 = "teams"
        const val id2 = "id"
        fun startActivity(context: Context,team: Team,id:String){
            context.startActivity<TeamDetail>(teams1 to team, id2 to id)
        }

        fun startActivityFav(context: Context,teamFavorite: TeamFavorite){
            context.startActivity<TeamDetail>(teams1 to teamFavorite)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.addFavourite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            databaseTeam.use {
                insert(
                    TeamFavorite.TABLE_FAVORITE,
                    TeamFavorite.TEAM_ID to teams.teamId,
                    TeamFavorite.TEAM_NAME to teams.teamName,
                    TeamFavorite.TEAM_BADGE to teams.teamBadge,
                    TeamFavorite.TEAM_DESC to desc)
            }
            toast("Berhasil")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            databaseTeam.use {
                delete(TeamFavorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to id)
            }
            toast("Berhasil")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added)
    }
}

