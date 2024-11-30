package com.example.myfinalproject.screens


import android.widget.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfinalproject.order.Conference
import com.example.myfinalproject.order.Division
import com.example.myfinalproject.order.Team


@Composable
fun ConferencePicker(conferences: List<Conference>, onConferenceSelected: (Conference) -> Unit) {
    var selectedConference by remember { mutableStateOf<Conference?>(null) }
    LazyColumn {
        items(conferences) { conference ->
            Button(
                onClick = { onConferenceSelected(conference) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = conference.name)
            }
        }
    }
}

@Composable
fun DivisionPicker(divisions: List<Division>, onDivisionSelected: (Division) -> Unit) {
    var selectedDivision by remember { mutableStateOf<Division?>(null) }

    LazyColumn {
        items(divisions) { division ->
            Button(
                onClick = {
                    selectedDivision = division
                    onDivisionSelected(division)
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = division.name)
            }
        }
    }
}

@Composable
fun TeamPicker(teams: List<Team>, onTeamSelected: (Team) -> Unit) {
    var selectedTeam by remember { mutableStateOf<Team?>(null) }

    LazyColumn {
        items(teams) { team ->
            Button(
                onClick = {
                    selectedTeam = team
                    onTeamSelected(team)
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = team.Name)
            }
        }
    }
}
