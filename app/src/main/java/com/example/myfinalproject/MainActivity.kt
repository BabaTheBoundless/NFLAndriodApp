package com.example.myfinalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfinalproject.order.OrderViewModel
import com.example.myfinalproject.screens.ConferencePicker
import com.example.myfinalproject.screens.NFLApp
import com.example.myfinalproject.screens.TeamActivity
import com.example.myfinalproject.ui.theme.MyFinalProjectTheme

class MainActivity : ComponentActivity() {
    private val viewModel: OrderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           NFLApp()
        }


    }
}

@Composable
fun MainScreen(onNavigateToTeamActivity: () -> Unit) {
    Scaffold { paddingValues ->
        Button(
            onClick = { onNavigateToTeamActivity() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "Go to Team Activity")
        }
    }
}

@Composable
fun MyApp(viewModel: OrderViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // Display ConferencePicker once conferences are loaded
    if (uiState.conferences.isNotEmpty()) {
        ConferencePicker(
            conferences = uiState.conferences,
            onConferenceSelected = { selectedConference ->
                Log.d("MyApp", "Selected Conference: ${selectedConference.name}")
            }
        )
    } else {
        // Show a loading indicator while data is being fetched
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
