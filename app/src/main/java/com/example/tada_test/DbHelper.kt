package com.example.tada_test

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.tada_test.dependencyInjection.component.BaseApp
import com.example.tada_test.model.UserLogin
import java.lang.Exception
import javax.inject.Inject

class DBHelper @Inject constructor(context: BaseApp) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT," +
                PASSWORD + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(name : String, password : String ): String{

        try {
            val values = ContentValues()

            values.put(NAME, name)
            values.put(PASSWORD, password)

            val db = this.writableDatabase

            db.insert(TABLE_NAME, null, values)
            db.close()
            return "Success Add User"
        } catch (e: Exception) {
            throw e
        }

    }

    fun getUser(username : String, password: String): ArrayList<UserLogin> {
        val users = ArrayList<UserLogin>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from $TABLE_NAME WHERE $NAME ='$username' AND $PASSWORD = '$password'", null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }

        var name: String
        var pass: String
        var id: String
        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                pass = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD))
                id = cursor.getInt(cursor.getColumnIndexOrThrow(ID)).toString()

                users.add(UserLogin(id, name, pass))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return users
    }

    companion object{
        private val DATABASE_NAME = "TADA"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "user_table"

        val ID = "id"

        val NAME = "name"

        val PASSWORD = "password"
    }
}

