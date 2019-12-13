package com.sample.rxnaversearchapi

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("actor")
    val actor: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userRating")
    val userRating: String
)