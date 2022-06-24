package com.example.mobileapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 2
        private val DATABASE_NAME = "users_data.db"
        //Table
        private val TABLE_NAME = "users"
        private val COL_USERNAME = "username"
        private val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_USERNAME TEXT PRIMARY KEY, $COL_PASSWORD TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun addUser(user: User): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        val usernameInput = user.username
        val query = "SELECT * FROM users where username = \"$usernameInput\""
        val cursor = db.rawQuery(query, null)
        if (cursor.count == 0) {
            values.put(COL_USERNAME, user.username)
            values.put(COL_PASSWORD, user.password)

            db.insert("users", null, values)
            db.close()
            return true
        }
        return false
    }


    fun findUser(user: User): Boolean{
        val db = this.writableDatabase
        val usernameInput = user.username
        val query = "SELECT * FROM users where username = \"$usernameInput\""
        val cursor = db.rawQuery(query, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            val passwd = cursor.getString(1)
            if (passwd == user.password){
                return true
            }
        }
        return false
    }



}