package com.example.myapplication3

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.ArrayList

class ApiService(private val context: Context) {
//    private val dbHelper = DBHelper(context, null) // Добавлен DBHelper
    val list = ArrayList<modelM>()
    fun getMangaData(successCallback: (List<modelM>) -> Unit, errorCallback: (String) -> Unit) {
        val url = "https://kitsu.io/api/edge/manga"
//        val url = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=20"
//        val url3 = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=40"
//        val url4 = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=60"
//        val url5 = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=80"
//        val url6 = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=100"

        val queue = Volley.newRequestQueue(context)
//        val request = StringRequest(Request.Method.GET, url, { result ->
//            val list2 = parseMangaJson(result, successCallback, errorCallback)
//        }, { error ->
//            errorCallback("Error: ${error.message}")
//        })

        val listMas = listOf(10,20,40,50,60,70,80)


        for (i in listMas ){
            val url1 = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=$i"
            val request = StringRequest(Request.Method.GET, url1, { result ->
                val list2 = parseMangaJson(result, successCallback, errorCallback)
            }, { error ->
                errorCallback("Error: ${error.message}")
            })

            queue.add(request)
        }
//        queue.add(request)




    }

    private fun parseMangaJson(result: String, successCallback: (List<modelM>) -> Unit, errorCallback: (String) -> Unit): List<modelM> {

        val mainOb = JSONObject(result)
        val mainOb2 = mainOb.getJSONArray("data")
        var completedRequests = 0

        for (i in 0 until mainOb2.length()) {
            val mainOb3 = mainOb2.getJSONObject(i)

            val en = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("en", "null")
            val en_jp = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("en_jp", "null")
            val ja_jp = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("ja_jp", "null")
            val en_us = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("en_us", "null")
            val synopsis = mainOb3.getJSONObject("attributes").getString("synopsis")
            val ageRating = mainOb3.getJSONObject("attributes").getString("ageRating")
            val ageRatingGuide = mainOb3.getJSONObject("attributes").getString("ageRatingGuide")
            val posterImage = mainOb3.getJSONObject("attributes").getJSONObject("posterImage").optString("original", "null")

            // Получение жанров
            getMangaGenres(mainOb3.getString("id"), { genres ->
                val item = modelM(
                    mainOb3.getString("id"),
                    en,
                    en_jp,
                    ja_jp,
                    en_us,
                    synopsis,
                    ageRating,
                    ageRatingGuide,
                    posterImage,
                    genres
                )
                list.add(item)

//                val db = DBHelper(this, null)

//                dbHelper.addManga(item) // Добавляется манга

                completedRequests++
                // Уведомить о результате после добавления последнего элемента
                if (completedRequests == mainOb2.length()) {
                    successCallback(list)
                    Log.d("MyLog2", list.toString())
                }
            }, { error ->
                Log.d("MyLog", error)
                completedRequests++
                if (completedRequests == mainOb2.length()) {
                    successCallback(list)
                }
            })
        }

        return list
    }

    private fun getMangaGenres(id: String, successCallback: (List<genre>) -> Unit, errorCallback: (String) -> Unit) {
        val url = "https://kitsu.io/api/edge/manga/$id/genres"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url, { result ->
            val list = parseMangaGenres(result)
            successCallback(list)
        }, { error ->
            errorCallback("Error: ${error.message}")
        })

        queue.add(request)
    }

    private fun parseMangaGenres(result: String): List<genre> {
        val genres = ArrayList<genre>()
        val mainOb = JSONObject(result)
        val mainOb2 = mainOb.getJSONArray("data")

        for (i in 0 until mainOb2.length()) {
            val mainOb3 = mainOb2.getJSONObject(i)
            val item = genre(
                mainOb3.getString("id"),
                mainOb3.getJSONObject("attributes").getString("name")
            )
            genres.add(item)
        }

        return genres
    }
}

//class ApiService(private val context: Context) {
//
//    fun getMangaData(successCallback: (List<modelM>) -> Unit, errorCallback: (String) -> Unit) {
//        val url = "https://kitsu.io/api/edge/manga"
//
//        val queue = Volley.newRequestQueue(context)
//        val request = StringRequest(Request.Method.GET, url, { result ->
//            val list = parseMangaJson(result)
//            successCallback(list)
//        }, { error ->
//            errorCallback("Error: ${error.message}")
//        })
//
//        queue.add(request)
//    }
//
//    private fun parseMangaJson(result: String): List<modelM> {
//        val list = ArrayList<modelM>()
//        val mainOb = JSONObject(result)
//        val mainOb2 = mainOb.getJSONArray("data")
//
//        for (i in 0 until mainOb2.length()) {
//            val mainOb3 = mainOb2.getJSONObject(i)
//            val genres = ArrayList<genre>()
//
//            val en = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("en", "null")
//            val en_jp = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("en_jp", "null")
//            val ja_jp = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("ja_jp", "null")
//            val en_us = mainOb3.getJSONObject("attributes").getJSONObject("titles").optString("en_us", "null")
//
//
//
//            val item = modelM(
//                mainOb3.getString("id"),
//                en,
//                en_jp,
//                ja_jp,
//                en_us,
//                mainOb3.getJSONObject("attributes").getString("synopsis"),
//                mainOb3.getJSONObject("attributes").getString("ageRating"),
//                mainOb3.getJSONObject("attributes").getString("ageRatingGuide"),
//                mainOb3.getJSONObject("attributes").getJSONObject("posterImage").optString("original", "null"),
//                appGanre
//            )
//            list.add(item)
//        }
//
////        Log.d("MyLog2", list.toString())
//        return list
//    }
//        private fun getMangaGenres(id: String, successCallback: (List<genre>) -> Unit, errorCallback: (String) -> Unit) {
//            val url = "https://kitsu.io/api/edge/manga/$id/genres"
//
//            val queue = Volley.newRequestQueue(context)
//            val request = StringRequest(Request.Method.GET, url, { result ->
//                val list = parseMangaGenres(result)
//                successCallback(list)
//            }, { error ->
//                errorCallback("Error: ${error.message}")
//            })
//
//            queue.add(request)
//        }
//        private fun parseMangaGenres(result: String): List<genre> {
//            val genres = ArrayList<genre>()
//            val mainOb = JSONObject(result)
//            val mainOb2 = mainOb.getJSONArray("data")
//
//            for (i in 0 until mainOb2.length()) {
//                val mainOb3 = mainOb2.getJSONObject(i)
//                val item = genre(
//                    mainOb3.getString("id"),
//                    mainOb3.getString("attributes").getString("name")
//                )
//                genres.add(item)
//            }
//
//            return genres
//        }
//    private fun getMangaDataGanre(successCallback: (List<genre>) -> Unit,id:String) {
//        val url = "https://kitsu.io/api/edge/manga/$id/genres"
//
//        val queue1 = Volley.newRequestQueue(context)
//        val request = StringRequest(Request.Method.GET, url, { result ->
//            val list = parseMangaGanre(result)
//            successCallback(list)
//        }, { error ->
//            errorCallback("Error: ${error.message}")
//        })
//
//        queue1.add(request)
//    }
//    private fun parseMangaGanre(result: String): List<genre>{
//        val genres = ArrayList<genre>()
//        val mainOb = JSONObject(result)
//        val mainOb2 = mainOb.getJSONArray("data")
//
//        for (i in 0 until mainOb2.length()) {
//            val mainOb3 = mainOb2.getJSONObject(i)
//
//
//            val item = genre(
//                mainOb3.getString("id"),
//                mainOb3.getJSONObject("attributes").getString("name")
//            )
//            genres.add(item)
//        }
//
////        Log.d("MyLog2", list.toString())
//        return genres
//    }
//}
//    private  fun prsMang2(obj: JSONObject):List<modelM>{
//        val list = ArrayList<modelM>()
//
//        val mainOb2 = obj.getJSONArray("data")
//        Log.d("MyLog",mainOb2[0].toString())
//        Log.d("MyLog",mainOb2[1].toString())
//
//        for(i in 0 until mainOb2.length()) {
//            val mainOb3 = mainOb2[i] as JSONObject
//
//            val en = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("en")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("en")
//            val en_jp = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("en_jp")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("en_jp")
//            val ja_jp = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("ja_jp")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("ja_jp")
//            val en_us = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("en_us")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("en_us")
//            Log.d(
//                "MyLog",
//                mainOb3.getJSONObject("attributes").getJSONObject("titles").toString()
//            )
////
////
//            val item = modelM(
//                mainOb3.getString("id"),
//                en,
//                en_jp,
//                ja_jp,
//                en_us,
//                mainOb3.getJSONObject("attributes").getString("synopsis"),
//                mainOb3.getJSONObject("attributes").getString("ageRating"),
//                mainOb3.getJSONObject("attributes").getString("ageRatingGuide")
//
//            )
//            list.add(item)
//
//        }
//        Log.d("MyLog2", list.toString())
//        return list
//
//    }

