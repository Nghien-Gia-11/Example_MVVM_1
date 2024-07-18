package com.example.example_mvvm_1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountCharacterViewModel : ViewModel() {

    private val _result = MutableLiveData<HashMap<String, Int>>()
    val result : LiveData<HashMap<String, Int>> get() = _result

    fun countString(input : String){
        val hashMap = HashMap<String, Int>()
        for (i in input) {
            if (hashMap.containsKey("$i")) {
                hashMap["$i"] = (hashMap["$i"] ?: 1) + 1
            } else {
                hashMap["$i"] = 1
            }
        }
        _result.value = hashMap
    }

}