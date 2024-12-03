package com.example.myfinalproject.order

data class Team(
    val Name: String, //team name i.e, Tennessee Titans
    val Conference: String,
    val Division: String,
    val Team: String, //team abbreviation i,e, TEN
    val Wins: Int,
    val Losses: Int,
    val Ties: Int,
    val Percentage: Float,
    val PointsFor: Int, //points scored
    val PointsAgainst: Int, //points given up
    val DivisionRank: Int,
    val ConferenceRank: Int,
)

data class Conference(val name: String, val division: List<Division>)
data class Division(val name: String, val teams: List<Team>)