package com.example.myfinalproject.screens

import android.annotation.SuppressLint
import android.os.Bundle
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

        // Initialize ScrollView and LinearLayout
        val scrollView = ScrollView(this)
        linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        scrollView.addView(linearLayout)
        setContentView(scrollView)

        // Initialize ViewModel
        teamViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        // Observe data and populate TextViews
        teamViewModel.teamList.observe(this) { teams ->
            linearLayout.removeAllViews() // Clear previous data

            teams.forEach { team ->
                val textView = TextView(this).apply {
                    text = "Team: ${team.teamName}, Wins: ${team.wins}, Losses: ${team.losses}"
                    textSize = 16f
                    setPadding(8, 8, 8, 8)
                }
                linearLayout.addView(textView)
            }
        }
    }
}