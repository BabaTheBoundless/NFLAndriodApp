package com.example.myfinalproject.order

data class Team(
    val Name: String,
    val Conference: String,
    val Division: String,
    val Team: String,
    val Wins: Int,
    val Losses: Int,
    val Ties: Int,
    val Percentage: Float,
    val PointsFor: Int, //points scored
    val PointsAgainst: Int, //points given up
    val DivisionRank: Int,
    val ConferenceRank: Int,

)