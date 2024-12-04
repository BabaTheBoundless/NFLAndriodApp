package com.example.myfinalproject.order

data class OrderUiState(
    val conferences: List<Conference> = emptyList(),
    val selectedConference: Conference? = null,
    val selectedDivision: Division? = null,
    var selectedTeam: Team? = null,
    val teams: List<Team> = emptyList() //teams in the selected divisions
)

