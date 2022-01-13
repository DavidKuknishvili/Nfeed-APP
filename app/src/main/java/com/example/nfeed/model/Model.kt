package com.example.nfeed.model


import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)