package com.example.tinybudgetplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinyBudgetPlannerApp()
        }
    }
}

@Composable
fun TinyBudgetPlannerApp() {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val items = remember { mutableStateListOf<Pair<String, Double>>() }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Text("Tiny Budget Planner", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val amt = amount.toDoubleOrNull() ?: 0.0
            if (title.isNotBlank() && amt != 0.0) {
                items.add(title to amt)
                title = ""
                amount = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Entries:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        for ((itemTitle, itemAmount) in items) {
            Text("$itemTitle: $itemAmount")
        }

        Spacer(modifier = Modifier.height(24.dp))

        val total = items.sumOf { it.second }
        Text("Total: $total", style = MaterialTheme.typography.headlineSmall)
    }
}
