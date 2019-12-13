package com.sample.rxnaversearchapi

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val movieAdapter by lazy {
        MovieAdapter { url ->
            CustomTabsIntent.Builder()
                .build()
                .launchUrl(
                    this@MainActivity,
                    Uri.parse(url)
                )
        }
    }

    private val movieSearchApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieSearchApi::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            "${edt_search.text}".let { keyWord ->
                if (keyWord.isNotEmpty()) {
                    searchByKeyWord(keyWord)
                }
            }
        }

        rv_movie.adapter = movieAdapter


    }

    private fun searchByKeyWord(keyWord: String) {
        movieSearchApi.getMovieList(CLIENT_ID, SECRET_KEY, keyWord)
            .enqueue(object : Callback<MovieDataResponse> {
                override fun onResponse(
                    call: Call<MovieDataResponse>,
                    response: Response<MovieDataResponse>
                ) {
                    Log.d(TAG, "$response")

                    response.body()?.movieResponseList?.let { movieResponseList ->
                        movieAdapter.replaceAll(movieResponseList.map { it.toMovieItem() })
                    }
                }

                override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
                    Log.d(TAG, t.message.orEmpty())
                }
            })
    }

    companion object {
        const val TAG = "MainActivity"
        const val CLIENT_ID = "pqu5xvOzU1qikyXi94iM"
        const val SECRET_KEY = "i5W1ABuBft"
    }
}
