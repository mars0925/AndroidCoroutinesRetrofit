package com.example.androidcoroutinesretrofit.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("results")
    val users: List<User>
)

data class User(
    val email: String,
    val id: Id,
    val name: Name,
    val phone: String,
    val picture: Picture,
)



data class Id(
    val name: String,
    val value: String
)


data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
