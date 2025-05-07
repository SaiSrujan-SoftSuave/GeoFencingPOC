package dev.demo.kmp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val currentLocation by remember {
        mutableStateOf("")
    }
    var sliderValue by remember { mutableStateOf(0f) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("GeoFencing POC") },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    println("")
                }
            ){
                Text("Start Tracking")
            }
            Button(
                onClick = {
                    println("")
                }
            ){
                Text("Stop Tracking")
            }
            Column {
                Text(currentLocation)
                Text("Current Location", style = MaterialTheme.typography.subtitle1)
            }
            Column {
                Text("Fences Distance $sliderValue")
                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 5.dp),
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f
                )
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}