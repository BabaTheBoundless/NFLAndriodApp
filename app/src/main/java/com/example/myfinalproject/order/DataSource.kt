package com.example.myfinalproject.order

data class Team(
    val teamName: String,
    val conference: String,
    val division: String,
    val abbrev: String,
    val wins: Int,
    val losses: Int,
    val ties: Int,
    val winningPercentage: Float,
    val pointsFor: Int, //points scored
    val pointsAgainst: Int, //points given up
    val divisionRank: Int,
    val conferenceRank: Int,

)