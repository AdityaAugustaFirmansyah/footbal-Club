package com.example.aditya.matchscheduller.detailmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.api.ApiRepositery
import com.example.aditya.matchscheduller.sqlite.Favourite
import com.example.aditya.matchscheduller.sqlite.database
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import utils.invisible
import utils.visible

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : AppCompatActivity(), DetailMatchView {
    private lateinit var matchPresenter: detailMatchPresenter
    private var menuitem:Menu? = null
    private var isFavourite:Boolean = false
    private lateinit var id:String
    private lateinit var idHomeTeam:String
    private lateinit var idAwayTeam:String
    private lateinit var eventDetail: eventDetail
    private lateinit var date:String
    private lateinit var time:String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val gson = Gson()
        val apiRepositery = ApiRepositery()
        id = intent.getStringExtra("idEvent")

        idHomeTeam = intent.getStringExtra("idHome")
        idAwayTeam = intent.getStringExtra("idAway")
        date = intent.getStringExtra("date")
        time = intent.getStringExtra("time")

        Log.d("APAAA",date)
        Log.d("APAAA",time)

        cekFavorite()

        matchPresenter = detailMatchPresenter(this, gson, apiRepositery)
        matchPresenter.getMatchDetails(id)
        matchPresenter.getHomeTeamBadge(idHomeTeam)
        matchPresenter.getAwayTeamBadge(idAwayTeam)


    }
    private fun cekFavorite(){
        database.use {
            val result = select(Favourite.TABLE_NAME)
                .whereArgs("(ID_EVENT = {id})","id" to id)
            val favorite = result.parseList(classParser<Favourite>())
            if (!favorite.isEmpty()) isFavourite = true
        }
    }

    override fun showLoading() {
        progress_Bar.visible()
    }

    override fun hideLoading() {
        progress_Bar.invisible()
    }

    override fun showEventDetail(data: List<eventDetail>) {
        eventDetail = eventDetail(
            data[0].idEvent, data[0].idHome, data[0].idAway, data[0].strHomeTeam, data[0].strAwayTeam,
            data[0].intHomeScore, data[0].intAwayScore
        )

        dateDetail.text = data[0].dateEvent.toString()
        nameHome.text = data[0].strHomeTeam.toString()
        nameAway.text = data[0].strAwayTeam.toString()

        if (data[0].strHomeGoalDetails == null || data[0].strAwayGoalDetails == null){

        }else{
            homeLineupKiper.text = data[0].strHomeLineupGoalkeeper
            HomeLineupDefender.text = data[0].strHomeLineupDefense
            homeGoalDetail.text = data[0].strHomeGoalDetails
            HomeLineupMid.text = data[0].strHomeLineupMidfield
            HomeLineupSubs.text = data[0].strHomeSubstitutes
            HomeLineupForward.text = data[0].strHomeLineupForward
            awayGoalDetail.text = data[0].strAwayGoalDetails
            AwayLineupDefender.text = data[0].strAwayLineupDefense
            AwayLineupKiper.text = data[0].strAwayLineupGoalkeeper
            AwayLineupMid.text = data[0].strAwayLineupMidfield
            AwayLineupSubs.text = data[0].strAwayLineupSubstitutes
            AwayLineupForward.text = data[0].strAwayLineupForward
        }

        if (data[0].intHomeScore == null || data[0].intAwayScore == null){
            homeGoalScore.text = 0.toString()
            awayGoalScore.text = 0.toString()

        }else {
            homeGoalScore.text = data[0].intHomeScore.toString()
            awayGoalScore.text = data[0].intAwayScore.toString()
        }

    }

    override fun showHomeTeamDetail(data: List<homeTeamDetail>) {
        val url:String? = data[0].strTeamBadge.toString()
        Picasso.get().load(url).into(imgHome)
    }

    override fun showAwayTeamDetail(data: List<AwayTeamDetail>) {
        val url:String? = data[0].strTeamBadge.toString()
        Picasso.get().load(url).into(imgAway)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        menuitem = menu
        setFavorite()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.addFavourite ->{
                if (isFavourite)removeFavorite() else addFavorite()

                isFavourite = !isFavourite

                setFavorite()
                true

            }
            else -> super.onOptionsItemSelected(item)
        }

    }
   fun addFavorite(){
        try {
            if (eventDetail.intHomeScore == null || eventDetail.intAwayScore == null) {
                eventDetail.intHomeScore = "0"
                eventDetail.intAwayScore = "0"

                database.use {
                    insert(
                        Favourite.TABLE_NAME,
                        Favourite.ID_EVENT to eventDetail.idEvent,
                        Favourite.HOME_ID to eventDetail.idHome,
                        Favourite.AWAY_ID to eventDetail.idAway,
                        Favourite.HOME_NAME to eventDetail.strHomeTeam,
                        Favourite.AWAY_NAME to eventDetail.strAwayTeam,
                        Favourite.HOME_SCORE to eventDetail.intHomeScore,
                        Favourite.AWAY_SCORE to eventDetail.intAwayScore,
                        Favourite.DATE to date,
                        Favourite.TIME to time
                    )
                }
            }
            database.use {
                insert(Favourite.TABLE_NAME,
                    Favourite.ID_EVENT to eventDetail.idEvent,
                    Favourite.HOME_ID to eventDetail.idHome,
                    Favourite.AWAY_ID to eventDetail.idAway,
                    Favourite.HOME_NAME to eventDetail.strHomeTeam,
                    Favourite.AWAY_NAME to eventDetail.strAwayTeam,
                    Favourite.HOME_SCORE to eventDetail.intHomeScore,
                    Favourite.AWAY_SCORE to eventDetail.intAwayScore,
                    Favourite.DATE to date,
                    Favourite.TIME to time
                )
                Log.d("idEvent",eventDetail.idEvent)
                Log.d("idEvent",eventDetail.idHome)
                Log.d("idEvent",eventDetail.idAway)
                Log.d("idEvent",eventDetail.strHomeTeam)
                Log.d("idEvent",eventDetail.strAwayTeam)
                Log.d("idEvents",eventDetail.intHomeScore)
                Log.d("idEvents",eventDetail.intAwayScore)
                Log.d("idEventd", date)
                Log.d("idEventt",time)
            }

            toast("berhasil")
        }catch (e:SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

     fun removeFavorite(){
        try {
            database.use {
                delete(Favourite.TABLE_NAME,"(ID_EVENT = {id})","id" to id)
            }
            toast("berhasil")
        }catch (e:SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite(){
        if(isFavourite){
            menuitem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_added
            )
        }else{
            menuitem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_add
            )
        }
    }
}