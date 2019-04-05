package com.example.aditya.matchscheduller.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by root on 2/6/18.
 */
class FavoriteTeamOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: FavoriteTeamOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteTeamOpenHelper {
            if (instance == null) {
                instance = FavoriteTeamOpenHelper(ctx.applicationContext)
            }
            return instance as FavoriteTeamOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            TeamFavorite.TABLE_FAVORITE, true,
                TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamFavorite.TEAM_ID to TEXT + UNIQUE,
                TeamFavorite.TEAM_NAME to TEXT,
                TeamFavorite.TEAM_BADGE to TEXT,
                TeamFavorite.TEAM_DESC to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(TeamFavorite.TABLE_FAVORITE, true)
    }
}


// Access property for Context
val Context.databaseTeam: FavoriteTeamOpenHelper
    get() = FavoriteTeamOpenHelper.getInstance(applicationContext)