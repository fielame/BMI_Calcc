package com.example.bmi_calc

// Основные импорты для AlertDialog

// Для состояния диалога
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bmi_calc.screens.MainScreen
import com.example.bmi_calc.ui.theme.BMI_CalcTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMI_CalcTheme() {
                MainScreen(this)
            }
        }
    }
}