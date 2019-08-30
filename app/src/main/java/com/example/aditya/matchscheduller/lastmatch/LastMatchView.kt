package com.example.aditya.matchscheduller.lastmatch

interface LastMatchView{
    fun hideLoading()
    fun showLoading()
    fun showLastMatchList(data: List<LastMatch>)
    fun showErroe(error:String)
}
