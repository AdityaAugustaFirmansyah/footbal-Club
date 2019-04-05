package com.example.aditya.matchscheduller.view

import com.example.aditya.matchscheduller.data.NextMatch

interface  NextMatchView{
   fun showLoading()
   fun hideLoading()
   fun showNextMatchList(data:List<NextMatch>)
}
