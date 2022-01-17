package com.example.nfeed.network

import com.example.nfeed.model.Model
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=us&apiKey=a7559f027dd24ec4877daae9ac2313fc")
    suspend fun getNews(): Response<Model>
}