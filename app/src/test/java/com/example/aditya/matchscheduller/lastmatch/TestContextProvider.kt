package com.example.aditya.matchscheduller.lastmatch

import com.example.aditya.matchscheduller.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider:CoroutineContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
}