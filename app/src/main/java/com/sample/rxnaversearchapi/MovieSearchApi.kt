package com.sample.rxnaversearchapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieSearchApi {
    @GET("v1/search/movie.json")
    fun getMovieList(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") secretKey: String,
        @Query("query") keyWord: String
    ): Call<MovieDataResponse>
}