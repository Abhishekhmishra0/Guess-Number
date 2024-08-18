package com.example.guessnumbergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessnumbergame.ui.theme.GuessNumberGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessNumberGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessNumberApp()
                }
            }
        }
    }
}
@Composable
fun GuessNumberApp() {
    var userInput by remember { mutableStateOf("") }
    val randomNumber = remember { Random.nextInt(0, 11) }
    var attempts by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("") }
Box(modifier = Modifier
    .fillMaxSize()
    .background(Color(0xFFFFE4E1))){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFFFE4E1))
            .fillMaxSize()
    ) {
        Text(text = "Guess Number Game", fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp), color = Color.DarkGray)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Enter your guess") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            attempts++
            if (userInput.toIntOrNull() == randomNumber) {
                message = "You are lucky!"
            } else if (attempts >= 5) {
                message = "Better luck next time! The number was $randomNumber."
            } else {
                message = "You entered $userInput which doesn't match random number. Try Again"
            }
        }) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}
}
@Preview(showBackground = true)
@Composable
fun GuessNumberPreview(){
    GuessNumberApp()
}