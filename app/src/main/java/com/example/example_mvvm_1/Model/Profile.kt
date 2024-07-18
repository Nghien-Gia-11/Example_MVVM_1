package com.example.example_mvvm_1.Model

import com.google.gson.annotations.SerializedName

data class Profile(
    val success: Boolean,
    val message: String,
    @SerializedName("full_name")
    val fullName: String,
    val position: String,
    val history: List<History>
)
