package com.example.aditya.matchscheduller.API

import com.example.aditya.matchscheduller.BuildConfig

object TheSportDBApi{
    fun getLastMatches(league: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventspastleague.php?id="+league
    }

    fun getNextMatch(league: String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventsnextleague.php?id="+league
    }

    fun getEvenDetail(idEvent: String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupevent.php?id="+idEvent
    }

    fun getTeamBadge(team: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupteam.php?id="+team
    }

    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }

    fun getTeamDetail(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }

    fun getPlayer(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchplayers.php?t=" + teamId
    }

    fun getPlayerDetail(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupplayer.php?id=" + teamId
    }

    fun getDataSearchMatch(name:String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + name
    }

    fun getDataSearchTeam(name:String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + name
    }

}
