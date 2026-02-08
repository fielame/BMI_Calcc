package com.example.bmi_calc.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_calc.R
import com.example.bmi_calc.ui.theme.BMI_CalcTheme
import com.example.bmi_calc.ui.theme.font

@Composable
fun MainScreen(context: Context) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        var showAlert by remember { mutableStateOf(false) }

        BrandHeader()

        Spacer(Modifier.height(64.dp))

        var heightInput by remember { mutableStateOf("") }
        OutlinedTextField(
            value = heightInput,
            onValueChange = { newValue ->
                heightInput = newValue.replace(" ", "").replace(",", "")
            },
            label = { Text(stringResource(R.string.main_screen_height_input_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        var weightInput by remember { mutableStateOf("") }
        OutlinedTextField(
            value = weightInput,
            onValueChange = { newValue ->
                weightInput = newValue.replace(" ", "").replace(",", "")
            },
            label = { Text("Weight, kg") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = { showAlert = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(horizontal = 16.dp),
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

        if (showAlert) {
            val height = heightInput.toDoubleOrNull()
            val weight = weightInput.toDoubleOrNull()

            if (height == null || weight == null) {
                Toast.makeText(context,"Invalid input", Toast.LENGTH_LONG).show()
                showAlert = false
            } else {
                val bmiResult by remember { mutableDoubleStateOf(calculateBMI(weight, height)) }
                ResultDialog(
                    bmiResult = bmiResult,
                    bodyStatus = getBodyStatus(bmiResult),
                    onDismiss = { showAlert = false}
                )
            }
        }
    }
}

@Composable
fun BrandHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 76.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Logo()
        Text(
            text = ("BMI Calc"),
            color = Color.Black,
            modifier = Modifier.weight(1f),
            fontSize = 48.sp,
            fontFamily = font,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(180.dp),
        color = Color.Black,
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                painter = painterResource(R.drawable.new_logo),
                contentDescription = "LOGO",
                modifier = Modifier.size(200.dp),
                tint = Color.Unspecified
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BMI_CalcTheme { }
}