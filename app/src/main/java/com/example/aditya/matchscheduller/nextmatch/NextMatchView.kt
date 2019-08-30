package com.example.aditya.matchscheduller.nextmatch

import com.example.aditya.matchscheduller.nextmatch.NextMatch

interface NextMatchView{
   fun showLoading()
   fun hideLoading()
   fun showNextMatchList(data:List<NextMatch>)
   fun showError(error:String);
}
