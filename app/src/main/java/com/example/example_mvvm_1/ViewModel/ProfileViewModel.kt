package com.example.example_mvvm_1.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_mvvm_1.Model.Profile
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> get() = _profile

    init {
        readFileJson()
    }

    private fun readFileJson() {
        viewModelScope.launch {
            delay(2000)
            try {
                val inputStream = getApplication<Application>().assets.open("profile.json")
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                bufferedReader.forEachLine { line ->
                    stringBuilder.append(line) // thêm từng dòng vào stringBuilder
                }
                // convert từ string -> json
                _profile.value = Gson().fromJson(stringBuilder.toString(), Profile::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}