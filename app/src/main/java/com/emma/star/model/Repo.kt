package com.emma.star.model

import com.google.gson.annotations.SerializedName


data class Repo(
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("html_url") val url: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks_count") val forks: Int
)