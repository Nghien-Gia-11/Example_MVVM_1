package com.example.example_mvvm_1.Model

import com.google.gson.annotations.SerializedName


data class History(
    val title: String,
    @SerializedName("is_up")
    val isUp: Boolean
)
