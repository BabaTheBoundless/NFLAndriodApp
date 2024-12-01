package com.example.myfinalproject.order

data class OrderUiState(
    val conferences: List<Conference> = emptyList(),
    val selectedConference: Conference? = null,
    val selectedDivision: Division? = null,
    val selectedTeam: Team? = null,
)

