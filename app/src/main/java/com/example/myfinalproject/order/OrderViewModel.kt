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
        fetchTeamsInConference(conference)
    }

    fun onDivisionSelected(division: Division, conference: Conference) {
        _uiState.value = _uiState.value.copy(selectedDivision = division)
        fetchTeamsInDivisionAndConference(
            selectedDivision = division,
            selectedConference = conference)
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


    fun fetchTeamsInConference(selectedConference: Conference?) {
        viewModelScope.launch {
            try {
                //fetch teams from the API
                val response = RetrofitClient.apiService.getTeams(apiKey)

                //filter the teams to only include those in the selected conference
                val filteredTeams = response.filter { team ->
                    team.Conference == selectedConference?.name
                }

                Log.d("OrderViewModel", "Filtered teams: ${filteredTeams.size}")

                // Group teams by conference and division
                val conferences = response
                    .groupBy { it.Conference }
                    .map { (conferenceName, teams) ->
                        val divisions = teams.groupBy { it.Division }.map { (divisionName, divisionTeams) ->
                            Division(divisionName, divisionTeams)
                        }
                        Conference(conferenceName, divisions)
                    }

                // Update the UI state
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedConference = selectedConference, // Update selectedConference
                        teams = filteredTeams // Update the teams with the filtered list
                    )
                }

                // Update the LiveData or other stateful objects
                teamList.postValue(filteredTeams)

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("OrderViewModel", "Error in OrderViewModel: ${e.message}")
            }
        }
    }


    fun fetchTeamsInDivisionAndConference(selectedDivision: Division, selectedConference: Conference) {
        viewModelScope.launch {
            try {
                //fetch the teams from the API
                val response = RetrofitClient.apiService.getTeams(apiKey)

                //filter the teams by conference and division
                val filteredTeams = response.filter { team ->
                    team.Conference == selectedConference.name && team.Division == selectedDivision.name
                }

                Log.d("OrderViewModel", "Filtered teams: ${filteredTeams.size}")

                //group teams by conference and division (if needed for other UI updates)
                val conferences = response
                    .groupBy { it.Conference }
                    .map { (conferenceName, teams) ->
                        val divisions = teams.groupBy { it.Division }.map { (divisionName, divisionTeams) ->
                            Division(divisionName, divisionTeams)
                        }
                        Conference(conferenceName, divisions)
                    }

                //pdate the UI state
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedDivision = selectedDivision, //update selected division
                        teams = filteredTeams //update the teams with the filtered list
                    )
                }

                //update the live-data
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

