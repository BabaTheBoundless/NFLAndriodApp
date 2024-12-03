package com.example.myfinalproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.order.Conference
import com.example.myfinalproject.order.Division
import com.example.myfinalproject.order.OrderViewModel
import com.example.myfinalproject.order.Team

@Composable
fun TeamSelectionScreen(viewModel: OrderViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    //pick Conference
    var selectedConference by remember { mutableStateOf<Conference?>(null) }

    //pick Division
    var selectedDivision by remember { mutableStateOf<Division?>(null) }

    //pick Team
    var selectedTeam by remember { mutableStateOf<Team?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        //conference Picker
        if (selectedConference == null) {
            ConferencePicker(
                conferences = uiState.conferences,
                onConferenceSelected = { conference ->
                    selectedConference = conference
                    selectedDivision = null //reset division on conference change
                }
            )
        }

        //division Picker (only visible if a conference is selected)
        selectedConference?.let {
            DivisionPicker(
                divisions = it.division,
                onDivisionSelected = { division ->
                    selectedDivision = division
                }
            )
        }

        //team Picker (only visible if a division is selected)
        selectedDivision?.let {
            TeamPicker(
                teams = it.teams,
                onTeamSelected = { team ->
                    selectedTeam = team
                }
            )
        }

        //show selected team details
        selectedTeam?.let {
            DisplayTeamDetails(team = it)
            Text("hello")
        }
    }
}

@Composable
fun DisplayTeamDetails(team: Team) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Name: ${team.Name}", style = MaterialTheme.typography.displayMedium)
        Text("Conference: ${team.Conference}", style = MaterialTheme.typography.displayMedium)
        Text("Division: ${team.Division}", style = MaterialTheme.typography.displayMedium)
        Text("Wins: ${team.Wins}")
        Text("Losses: ${team.Losses}")
        Text("Ties: ${team.Ties}")
        Text("Win Percentage: ${team.Percentage}")
        Text("Points For: ${team.PointsFor}")
        Text("Points Against: ${team.PointsAgainst}")
        Text("Division Rank: ${team.DivisionRank}")
        Text("Conference Rank: ${team.ConferenceRank}")
    }
}
