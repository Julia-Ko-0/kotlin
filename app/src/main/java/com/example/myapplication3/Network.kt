package com.example.myapplication3

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

object Network {
//    val  url1 = "https://community-manga-eden.p.rapidapi.com/"
//    val urls = "${url1}list/0"
    val urls = "https://kitsu.io/api/edge/manga?page[limit]=20&page[offset]=0"
//    val url2 = "${url1}/manga/%7Bmanga.id%7D"
//    val urls = "https://myanimelist.p.rapidapi.com/manga/top/manga"
//    val urls = "https://kinopoiskapiunofficial.tech/api/v2.2/films"
//    val urls = "https://mangaverse-api.p.rapidapi.com/manga/fetch"


    val httpClient = HttpClient(OkHttp){
        install(ContentNegotiation){
            gson()
        }
    }
}