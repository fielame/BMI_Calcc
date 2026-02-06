package com.example.bmi_calc

import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_calc.ui.theme.BMI_CalcTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
// Основные импорты для AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button // если нужны обычные кнопки
import androidx.compose.foundation.layout.Box

// Для состояния диалога
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight



val font = FontFamily(
    Font(R.font.romantica, weight = FontWeight.Bold)
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMI_CalcTheme() {
                MainScreen()
            }
        }
    }
}
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 200.dp, top = 75.dp)
                .height(200.dp),
            color = Color.Black,
            shape = RoundedCornerShape(30.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    painter = painterResource(R.drawable.new_logo),
                    contentDescription = "LOGO",
                    modifier = Modifier
                        .size(200.dp),
                    tint = Color.Unspecified
                )
            }

        }
        Text(
            text = ("BMI Calc"),
            color = Color.Black,
            modifier = Modifier
                .padding(start = 250.dp, top = 100.dp),
            fontSize = 48.sp,
            fontFamily = font
        )
    }
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 256.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        var showAlert by remember { mutableStateOf(false) }
        var height by remember { mutableStateOf(0.0) }
        var weight by remember { mutableStateOf(0.0) }
        var weightInput by remember { mutableStateOf("") }
        var heightInput by remember { mutableStateOf("") }
        val results = false
        var bmiResult by remember { mutableStateOf(0.0) }


        fun calculateBMI(weight: Double, height: Double) {
            val bmi = weight / ((height / 100.0) * (height / 100.0))
            bmiResult = bmi
            showAlert = true
            }




        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 64.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = heightInput,
                onValueChange = { newValue ->
                    heightInput = newValue.filter { it.isDigit() || it == '.' }
                    height = heightInput.toDoubleOrNull() ?: 0.0
                },
                label = { Text("Height, cm") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = weightInput,
                onValueChange = { newValue ->
                    weightInput = newValue.filter { it.isDigit() || it == '.' }
                    weight = weightInput.toDoubleOrNull() ?: 0.0
                },
                label = { Text("Weight, kg") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )







            Button(
                onClick = {
                    calculateBMI(weight, height)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 50.dp, end = 16.dp, start = 16.dp, bottom = 325.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = ("Calculate your BMI"),
                    color = Color.White,
                    fontSize = 35.sp,
                    fontFamily = font
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            //Text(
            //text = "Your bmi result: $bmiResult"
            //)
        }

        val bodyStatus = remember { mutableStateOf("") }

        when(bmiResult){
            in 0.0 .. 18.5 -> bodyStatus.value = "deficiency, below normal."
            in 18.5 .. 24.9 -> bodyStatus.value = "healthy body weight (optimal range)."
            in 25.0 .. 29.9 -> bodyStatus.value = "overweight (pre-obesity)."
            in 30.0 .. 34.9 -> bodyStatus.value = "obesity stage I."
            in 35.0 .. 39.9 -> bodyStatus.value = "obesity stage II."
            else -> bodyStatus.value = "obesity grade III (morbid)."

        }


        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                title = { Text("Your BMI result") },
                text = { Text("${"%.2f".format(bmiResult)} - is your result\nYour body-status is ${bodyStatus.value}") },
                confirmButton = {
                    TextButton(onClick = { showAlert = false }) {
                        Text("OK")
                    }
                }
            )
        }

        }
    }





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMI_CalcTheme {

    }
}