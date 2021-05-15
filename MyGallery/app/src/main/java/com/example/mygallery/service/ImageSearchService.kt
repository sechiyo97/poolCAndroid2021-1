package com.example.mygallery.service

import com.example.mygallery.GalleryConst.BASE_URL
import com.example.mygallery.GalleryConst.NAVER_API_ID
import com.example.mygallery.GalleryConst.NAVER_API_SECRET
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by seheelee on 2021-05-04.
 */

val retrofit = Retrofit.Builder()
    .addConverterFactory(
        GsonConverterFactory.create()
    )
    .baseUrl(BASE_URL)
    .build()

interface ImageSearchService {
    @Headers("X-Naver-Client-Id: $NAVER_API_ID", "X-Naver-Client-Secret: $NAVER_API_SECRET")
    @GET("/v1/search/image")
    fun searchImages(
        @Query("query") query : String,
        @Query("display") display: Int
    ) : Call<ImageSearchResponse>
}

object ImageSearchAPI {
    val retrofitService : ImageSearchService by lazy {
        retrofit.create(ImageSearchService::class.java)
    }
}