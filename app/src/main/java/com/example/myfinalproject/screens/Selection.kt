package com.example.myfinalproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.order.Conference
import com.example.myfinalproject.order.Division
import com.example.myfinalproject.order.OrderViewModel
import com.example.myfinalproject.order.Team


@Composable
fun DisplayTeamDetails(team: Team) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //team Name
            Text(
                text = team.Name,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Divider()

            //conference and Division
            Text(
                text = "Conference: ${team.Conference}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Division: ${team.Division}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            //record
            Text(
                text = "Record: ${team.Wins}-${team.Losses}-${team.Ties}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            val myPercentageColor = if (team.Percentage < 0.500) {
                Color.Red

            } else {
                Color.Green
            }
            Text(
                text = "Win Percentage: ${team.Percentage}%",
                style = MaterialTheme.typography.bodyLarge,
                //color = myPercentageColor
            )

            Divider()

            //points and rankings
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Points For: ${team.PointsFor}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Points Against: ${team.PointsAgainst}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Divider()

            //rankings
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Division Rank: ${team.DivisionRank}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Conference Rank: ${team.ConferenceRank}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
