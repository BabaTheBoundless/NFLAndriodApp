package com.example.myfinalproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfinalproject.screens.TeamActivity
import com.example.myfinalproject.ui.theme.MyFinalProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           MyFinalProjectTheme {
               MainScreen {
                   val intent = Intent(this, TeamActivity::class.java)
                   startActivity(intent)
               }
           }
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