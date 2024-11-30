package com.example.myfinalproject.order

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinalproject.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()


    val apiKey = "050f76fd2b784f7db88f162dd4932831"

    val teamList = MutableLiveData<List<Team>>()

    fun fetchTeams() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTeams(apiKey)
                val teamNames = response.map { it.Name }

                _uiState.update { currentState ->
                    currentState.copy(Name = teamNames.toString())
                }


                Log.d("OrderViewModel", "team names: $teamNames$" )
                Log.d("OrderViewModel", "Fetched teams: ${response.size}")
                teamList.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("OrderViewModel", "Error in OrderViewModel: ${e.message}")
            }
        }
    }

}