package com.example.myapplication3

data class modelM(
    val id:String,
    val name_en:String,
    val name_en_jp:String,
    val name_ja_jp:String,
    val name_en_us:String,
    val synopsis:String,
    val ageRating:String,
    val ageRatingGuide:String,
    val image:String,
    val ganre: List<genre>
)
