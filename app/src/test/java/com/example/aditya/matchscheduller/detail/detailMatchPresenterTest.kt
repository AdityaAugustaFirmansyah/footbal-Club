package com.example.aditya.matchscheduller.detail

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.data.*
import com.example.aditya.matchscheduller.lastmatch.TestContextProvider
import com.example.aditya.matchscheduller.presenter.detailMatchPresenter
import com.example.aditya.matchscheduller.view.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class detailMatchPresenterTest {

    @Mock
    private
    lateinit var view: DetailMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepositery

    private lateinit var presenter: detailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = detailMatchPresenter(view, gson, apiRepository, TestContextProvider())
    }



    @Test
    fun testGetMatchDetails() {
        val teams: MutableList<eventDetail> = mutableListOf()
        val response = evenDetailResponse(teams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEvenDetail(id)).await(),
                evenDetailResponse::class.java
            )).thenReturn(response)

            presenter.getMatchDetails(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventDetail(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getHomeTeamBadge() {
        val teams: MutableList<homeTeamDetail> = mutableListOf()
        val response = homeTeamDetailResponse(teams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamBadge(id)).await(),
                homeTeamDetailResponse::class.java
            )).thenReturn(response)

            presenter.getMatchDetails(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showHomeTeamDetail(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getAwayTeamBadge() {
        val teams: MutableList<AwayTeamDetail> = mutableListOf()
        val response = awayTeamResponse(teams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamBadge(id)).await(),
                awayTeamResponse::class.java
            )).thenReturn(response)

            presenter.getMatchDetails(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showAwayTeamDetail(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}
