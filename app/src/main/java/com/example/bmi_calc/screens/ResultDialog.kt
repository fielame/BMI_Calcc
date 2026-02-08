package com.example.bmi_calc.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ResultDialog(
    bmiResult: Double,
    bodyStatus: BodyStatus,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Your BMI result") },
        text = { Text("${"%.2f".format(bmiResult)} - is your result\nYour body-status is ${bodyStatus.description}") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}