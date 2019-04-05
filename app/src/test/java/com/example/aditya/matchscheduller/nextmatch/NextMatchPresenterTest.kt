package com.example.aditya.matchscheduller.nextmatch

import com.example.aditya.matchscheduller.API.ApiRepositery
import com.example.aditya.matchscheduller.lastmatch.TestContextProvider
import com.example.aditya.matchscheduller.presenter.NextMatchPresenter
import com.example.aditya.matchscheduller.view.NextMatchView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepositery: ApiRepositery

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, gson,apiRepositery,TestContextProvider())
    }

    @Test
    fun testGetNextMatch() {
    }
}