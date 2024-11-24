package com.example.myfinalproject.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinalproject.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    val teamList = MutableLiveData<List<Team>>()

    fun fetchTeams() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTeams()
                teamList.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}