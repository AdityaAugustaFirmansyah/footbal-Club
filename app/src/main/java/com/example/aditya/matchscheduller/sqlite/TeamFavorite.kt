package com.example.aditya.matchscheduller.sqlite

data class TeamFavorite(val id: Long?, val teamId: String?, val teamName: String?, val teamBadge: String?, val teamDesc:String?)
     {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_DESC:String = "TEAM_DESC"
    }
}