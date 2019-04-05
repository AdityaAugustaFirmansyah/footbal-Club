package com.example.aditya.matchscheduller.lastmatch

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.API.TheSportDBApi
import com.example.aditya.matchscheduller.data.LastMatch
import com.example.aditya.matchscheduller.data.LastMatchResponse
import com.example.aditya.matchscheduller.presenter.LastMatchPresenter
import com.example.aditya.matchscheduller.view.LastMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest{
    @Mock
    private lateinit var view:LastMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepositery: ApiRepositery

    private lateinit var presenter: LastMatchPresenter
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view,gson,apiRepositery,TestContextProvider())
    }
    @Test
    fun testGetLastMatch(){
        val match:MutableList<LastMatch> = mutableListOf()
        val response = LastMatchResponse(match)
        val league = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepositery.doRequest(TheSportDBApi.getLastMatches(league)).await(),
                LastMatchResponse::class.java)).thenReturn(response)

            presenter.getLastMatch(league)

            Mockito.verify(view.showLoading())
            Mockito.verify(view.showLastMatchList(match))
            Mockito.verify(view.hideLoading())
        }
    }

}