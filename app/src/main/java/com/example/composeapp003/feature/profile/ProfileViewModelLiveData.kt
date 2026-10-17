package com.example.composeapp003.feature.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModelLiveData: ViewModel() {
    var count: MutableLiveData<Int> = MutableLiveData(0)

    fun increaseCount() {
        count.value = count.value!! + 1
    }
}