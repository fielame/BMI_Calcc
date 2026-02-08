package com.example.bmi_calc.screens

enum class BodyStatus(val description: String) {
    DEFICIENCY("deficiency, below normal"),
    NORMAL("healthy body weight (optimal range)"),
    OVERWEIGHT("overweight (pre-obesity)"),
    OBESITY1("obesity stage I"),
    OBESITY2("obesity stage II"),
    OBESITY3("obesity stage III"),
}

fun getBodyStatus(bmiResult: Double): BodyStatus {
    if (bmiResult < 0) throw IllegalArgumentException("bmiResult can't be negative")

    return when (bmiResult) {
        in 0.0..18.5 -> BodyStatus.DEFICIENCY
        in 18.5..24.9 -> BodyStatus.NORMAL
        in 25.0..29.9 -> BodyStatus.OVERWEIGHT
        in 30.0..34.9 -> BodyStatus.OBESITY1
        in 35.0..39.9 -> BodyStatus.OBESITY2
        else -> BodyStatus.OBESITY3
    }
}

fun calculateBMI(weight: Double, height: Double) = weight / ((height / 100.0) * (height / 100.0))
