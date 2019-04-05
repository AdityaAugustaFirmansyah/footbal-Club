package com.example.aditya.matchscheduller.API

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepositery{

    fun doRequest(url: String) : Deferred<String> = GlobalScope.async{
        URL(url).readText()
    }

}
