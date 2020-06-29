package com.example.dagger2_practical.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("username")
    @Expose
    var userName: String? = null,
    @SerializedName("email")
    @Expose
    var email: String? = null,
    @SerializedName("website")
    @Expose
    var website: String? = null) {}