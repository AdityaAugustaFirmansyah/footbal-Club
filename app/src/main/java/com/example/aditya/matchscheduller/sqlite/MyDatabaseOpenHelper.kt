package com.example.aditya.matchscheduller.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx:Context):ManagedSQLiteOpenHelper(ctx,"favorite_db",null,1) {
    companion object {

        private var instance:MyDatabaseOpenHelper? =null
        @Synchronized
        fun getInstance(ctx: Context):MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
                return instance as MyDatabaseOpenHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favourite.TABLE_NAME,true,
            Favourite.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
            Favourite.ID_EVENT to TEXT+ UNIQUE,
            Favourite.HOME_ID to TEXT,
            Favourite.AWAY_ID to TEXT,
            Favourite.HOME_NAME to TEXT,
            Favourite.AWAY_NAME to TEXT,
            Favourite.HOME_SCORE to TEXT,
            Favourite.AWAY_SCORE to TEXT,
            Favourite.DATE to TEXT,
            Favourite.TIME to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favourite.TABLE_NAME)
    }
}

val Context.database:MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)