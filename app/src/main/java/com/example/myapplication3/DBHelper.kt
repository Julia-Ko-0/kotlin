package com.example.myapplication3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createUsersTableQuery = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        val createMangaTableQuery = "CREATE TABLE manga (id INT PRIMARY KEY,name_en TEXT,name_en_jp TEXT,name_ja_jp TEXT,name_en_us TEXT,synopsis TEXT,ageRating TEXT,ageRatingGuide TEXT,img TEXT)"
//        val createUserMangaTableQuery = "CREATE TABLE userManga (id_user TEXT,id_manga TEXT,status TEXT ,PRIMARY KEY (id_user, id_manga))"
        val createUserMangaTableQuery = "CREATE TABLE userManga (id INT PRIMARY KEY,id_user TEXT,id_manga TEXT,status TEXT,name TEXT,synopsis TEXT,desk_m TEXT,ageRating TEXT ,img TEXT,name_en_jp TEXT,name_ja_jp TEXT,name_en_us TEXT)"
        db!!.execSQL(createUsersTableQuery)
        db!!.execSQL(createMangaTableQuery)
        db.execSQL(createUserMangaTableQuery)



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        db!!.execSQL("DROP TABLE IF EXISTS manga")
        db!!.execSQL("DROP TABLE IF EXISTS userManga")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues().apply {
            put("login", user.login)
            put("email", user.email)
            put("pass", user.pass)
//            put("status", user.status)


        }

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    fun addManga(manga: modelM) {
        val values = ContentValues().apply {
            put("id", manga.id)
            put("name_en", manga.name_en)
            put("name_en_jp", manga.name_en_jp)
            put("name_ja_jp", manga.name_ja_jp)
            put("name_en_us", manga.name_en_us)
            put("synopsis", manga.synopsis)
            put("ageRating", manga.ageRating)
            put("ageRatingGuide", manga.ageRatingGuide)
        }

        val db = this.writableDatabase
        db.insert("manga", null, values)
        db.close()
    }
    fun recordExists(id_user: String, id_manga: String,status:String): Boolean {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var exists = false

        try {
            cursor = db.rawQuery(
                "SELECT 1 FROM userManga WHERE id_user = ? AND id_manga = ? AND status = ? ",
                arrayOf(id_user, id_manga, status)
            )
            exists = cursor.moveToFirst()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }

        return exists
    }
    fun recordExists2(id_user: String, id_manga: String): Boolean {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var exists = false

        try {
            cursor = db.rawQuery(
                "SELECT 1 FROM userManga WHERE id_user = ? AND id_manga = ? ",
                arrayOf(id_user, id_manga)
            )
            exists = cursor.moveToFirst()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }

        return exists
    }
    fun addUserManga(userManga: userMangas) {
        if (!recordExists2(userManga.id_user, userManga.id)) {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put("id_user", userManga.id_user)
            contentValues.put("id_manga", userManga.id)
            contentValues.put("status", userManga.status)
            contentValues.put("name", userManga.name_en)
            contentValues.put("synopsis", userManga.synopsis)
            contentValues.put("desk_m", userManga.ganre_)
            contentValues.put("ageRating", userManga.ageRating)
            contentValues.put("img", userManga.image)
            contentValues.put("name_en_jp", userManga.name_en_jp)
            contentValues.put("name_ja_jp", userManga.name_ja_jp)
            contentValues.put("name_en_us", userManga.name_en_us)

            db.insert("userManga", null, contentValues)
            db.close()
        } else {
            Log.d("MyLog11", "Запись уже существует")
            if (!recordExists(userManga.id_user, userManga.id,userManga.status)){
                upgradeManga(userManga.status,userManga.id,userManga.id_user)
            }
            else{
                Log.d("MyLog11","njn ;t cnfnec")
            }
        }
    }
//    fun addUserManga(userManga: userMangas) {
//        val values = ContentValues().apply {
//            put("id_user", userManga.id_user)
//            put("id_manga", userManga.id)
//            put("status", userManga.status)
//            put("name", userManga.name_en)
//            put("synopsis", userManga.synopsis)
//            put("desk_m", userManga.ganre_)
//            put("ageRating", userManga.ageRating)
//            put("img", userManga.image)
//            put("name_en_jp", userManga.name_en_jp)
//            put("name_ja_jp", userManga.name_ja_jp)
//            put("name_en_us", userManga.name_en_us)
//
//
//
//        }
//        Log.d("MyLog3", values.toString())
//        val db = this.writableDatabase
//        db.insert("userManga", null, values)
//        db.close()
//
//    }


    fun getUser(login: String, pass: String): Boolean {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM users WHERE login = ? AND pass = ?", arrayOf(login, pass))
        val exists = result.moveToFirst()
        result.close()
        db.close()
        return exists
    }
    fun getUserMangaListSt(_id_user: String, _id: String): String {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        var userMangaStat = ""

        try {
            cursor = db.rawQuery(
                "SELECT status FROM userManga WHERE id_user = ? AND id_manga = ?",
                arrayOf(_id_user, _id)
            )

            if (cursor.moveToFirst()) {
                userMangaStat = cursor.getString(cursor.getColumnIndexOrThrow("status"))
            }
        } catch (e: Exception) {
            // Обработка возможных исключений
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }

        return userMangaStat
    }
//    fun getUserMangaListSt(_id_user: String, _id: String): String {
//        val db = this.readableDatabase
//        var cursor: Cursor? = null
//
//        try {
//            cursor = db.rawQuery(
//                "SELECT * FROM userManga WHERE id_user = ? AND id = ?",
//                arrayOf(_id_user, _id)
//            )
//
//            if (cursor.moveToFirst()) {
//                do {
//                    val userMangaStat = cursor.getString(cursor.getColumnIndexOrThrow("status"))
//                } while (cursor.moveToNext())
//            }
//        } catch (e: Exception) {
//            // Обработка возможных исключений
//            e.printStackTrace()
//        } finally {
//            cursor?.close()
//            db.close()
//        }
//
//        return userMangaStat
//    }
fun getUserManga(_id_user: String, _status: String): List<userMangas> {
    val userMangaList = ArrayList<userMangas>()
    val db = this.readableDatabase
    var cursor: Cursor? = null

    try {
        cursor = db.rawQuery(
            "SELECT * FROM userManga WHERE id_user = ? AND status = ?",
            arrayOf(_id_user, _status)
        )

        if (cursor.moveToFirst()) {
            do {
                val userManga = userMangas(
                    cursor.getString(cursor.getColumnIndexOrThrow("id_user")),
                    cursor.getString(cursor.getColumnIndexOrThrow("id_manga")),
                    cursor.getString(cursor.getColumnIndexOrThrow("status")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("synopsis")),
                    cursor.getString(cursor.getColumnIndexOrThrow("desk_m")),
                    cursor.getString(cursor.getColumnIndexOrThrow("ageRating")),
                    cursor.getString(cursor.getColumnIndexOrThrow("img")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name_en_jp")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name_ja_jp")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name_en_us"))
                )
                userMangaList.add(userManga)
            } while (cursor.moveToNext())
        }
    } catch (e: Exception) {
        // Обработка возможных исключений
        e.printStackTrace()
    } finally {
        cursor?.close()
        db.close()
    }

    return userMangaList
}
    fun getUserMangaList(_id_user: String): List<userMangas> {
        val userMangaList = ArrayList<userMangas>()
        val db = this.readableDatabase
        var cursor: Cursor? = null

//    // Проверка на существование записи

        try {
            cursor = db.rawQuery(
                "SELECT * FROM userManga WHERE id_user = ? ",
                arrayOf(_id_user)
            )

            if (cursor.moveToFirst()) {
                do {
                    val userManga = userMangas(
                        cursor.getString(cursor.getColumnIndexOrThrow("id_user")),
                        cursor.getString(cursor.getColumnIndexOrThrow("id_manga")),
                        cursor.getString(cursor.getColumnIndexOrThrow("status")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("synopsis")),
                        cursor.getString(cursor.getColumnIndexOrThrow("desk_m")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ageRating")),
                        cursor.getString(cursor.getColumnIndexOrThrow("img")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name_en_jp")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name_ja_jp")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name_en_us"))
                    )
                    userMangaList.add(userManga)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            // Обработка возможных исключений
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }

        return userMangaList
    }

    fun upgradeManga(_status: String, _id_manga: String, _id_user: String) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put("status", _status)
        }

        db.update(
            "userManga",
            values,
            "id_manga = ? AND id_user = ?",
            arrayOf(_id_manga, _id_user)
        )

        db.close()
    }
}
//fun addUserManga(userManga: userManga) {
//    val db = this.writableDatabase
//
//    // Проверка на существование записи
//    val cursor = db.rawQuery(
//        "SELECT * FROM userManga WHERE id_user = ? AND id_manga = ?",
//        arrayOf(userManga.id_user, userManga.id_manga)
//    )
//
//    if (cursor.count == 0) { // Если запись не существует, добавляем новую
//        val values = ContentValues().apply {
//            put("id_user", userManga.id_user)
//            put("id_manga", userManga.id_manga)
//            put("status", userManga.status)
//        }
//        db.insert("userManga", null, values)
//    } else { // Если запись существует, обновляем статус
//        val values = ContentValues().apply {
//            put("status", userManga.status)
//        }
//        db.update(
//            "userManga",
//            values,
//            "id_user = ? AND id_manga = ?",
//            arrayOf(userManga.id_user, userManga.id_manga)
//        )
//    }
//    cursor.close()
//    db.close()
//}
//    fun updateUserStatus(login: String, status: String) {
//        val db = this.writableDatabase
//        db.beginTransaction()
//        try {
//            // Установить статус false для всех записей
//            val valuesReset = ContentValues()
//            valuesReset.put("status", "0")
//            db.update("users", valuesReset, null, null)
//
//            // Установить новый статус для пользователя с данным логином
//            val valuesUpdate = ContentValues()
//            valuesUpdate.put("status", status)
//            db.update("users", valuesUpdate, "login = ?", arrayOf(login))
//
//            db.setTransactionSuccessful()
//        } finally {
//            db.endTransaction()
//        }
//        db.close()
//    }

//    fun getManga(): List<modelM> {
//        val db = this.readableDatabase
//        val cursor: Cursor = db.rawQuery("SELECT * FROM manga", null)
//        val mangaList = ArrayList<modelM>()
//
//        if (cursor.moveToFirst()) {
//            do {
//                val manga = modelM(
//                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("name_en")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("name_en_jp")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("name_ja_jp")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("name_en_us")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("synopsis")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("ageRating")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("ageRatingGuide")),
//                    null // genres are not fetched in this query
//                )
//                mangaList.add(manga)
//            } while (cursor.moveToNext())
//        }
//
//        cursor.close()
//        db.close()
//        return mangaList
//    }

//    fun getUserManga(_id_user: String, _status: String): List<userManga> {
//        val db = this.readableDatabase
//        val cursor: Cursor = db.rawQuery(
//            "SELECT * FROM userManga WHERE id_user = ? AND status = ?",
//            arrayOf(_id_user, _status)
//        )
//        val userMangaList = ArrayList<userManga>()
//
//        if (cursor.moveToFirst()) {
//            do {
//                val userManga = userManga(
//                    cursor.getString(cursor.getColumnIndexOrThrow("id_user")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("id_manga")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("status"))
//                )
//                userMangaList.add(userManga)
//            } while (cursor.moveToNext())
//        }
//
//        cursor.close()
//        db.close()
//        return userMangaList
//    }