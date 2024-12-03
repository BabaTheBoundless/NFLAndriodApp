package com.example.myfinalproject.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myfinalproject.order.OrderViewModel

class TeamActivity : AppCompatActivity() {


    private lateinit var teamViewModel: OrderViewModel

    @SuppressLint("StaticFieldLeak")
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize ScrollView and LinearLayout
        val scrollView = ScrollView(this)
        linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        Log.d("HelloTag", "hi")
        scrollView.addView(linearLayout)
        setContentView(scrollView)


        //initialize ViewModel
        teamViewModel = ViewModelProvider(this)[OrderViewModel::class.java]


        Log.d("HelloTag", "still working?")
        //observe data and populate TextViews
        teamViewModel.teamList.observe(this) { teams ->
            Log.d("HelloTag", "WORKING")
            linearLayout.removeAllViews() //clear previous data

            Log.d("TeamActivity", "Teams: $teams")
            Log.d("HelloTag", "heloo world")

            teams.forEach { team ->
                val textView = TextView(this).apply {
                    text = "Team: ${team.Name}, Wins: ${team.Wins}, Losses: ${team.Losses}"
                    textSize = 16f
                    setPadding(8, 8, 8, 8)
                }
                linearLayout.addView(textView)
            }
        }
        teamViewModel.fetchTeams()
    }
}