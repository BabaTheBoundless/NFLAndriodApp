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


    fun onConferenceSelected(conference: Conference) {
        _uiState.value = _uiState.value.copy(selectedConference = conference)
    }

    fun onDivisionSelected(division: Division) {
        _uiState.value = _uiState.value.copy(selectedDivision = division)

        fetchTeamsInDivision(division)
    }


    fun fetchTeamsInDivision(selectedDivision: Division?) {
        viewModelScope.launch {
            try {
                // Fetch teams from the API
                val response = RetrofitClient.apiService.getTeams(apiKey)

                // Filter the teams to only include those in the selected division
                val filteredTeams = response.filter { team ->
                    team.Division == selectedDivision?.name
                }

                // Optionally log the filtered teams (or the count)
                Log.d("OrderViewModel", "Filtered teams: ${filteredTeams.size}")

                // If there are any teams for the selected division, update the UI state
                val conferences = response
                    .groupBy { it.Conference }
                    .map { (conferenceName, teams) ->
                        val divisions = teams.groupBy { it.Division }.map { (divisionName, divisionTeams) ->
                            Division(divisionName, divisionTeams)
                        }
                        Conference(conferenceName, divisions)
                    }

                // Update the UI state with the filtered conferences and teams
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedDivision = selectedDivision, // Assign the selected division
                        teams = filteredTeams // Update the teams with the filtered list
                    )
                }

                // Update the state with only the filtered teams
                teamList.postValue(filteredTeams)

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("OrderViewModel", "Error in OrderViewModel: ${e.message}")
            }
        }
    }




    fun fetchTeams() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTeams(apiKey)
                val teamNames = response.map { it.Name }

                val conferences = response
                    .groupBy { it.Conference }
                    .map { (conferenceName, teams) ->
                        val divisions = teams.groupBy { it.Division }.map { (divisionName, divisionTeams) ->
                            Division(divisionName, divisionTeams)
                        }
                        Conference(conferenceName, divisions)
                    }

                _uiState.update { currentState ->
                    currentState.copy(conferences = conferences)
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

