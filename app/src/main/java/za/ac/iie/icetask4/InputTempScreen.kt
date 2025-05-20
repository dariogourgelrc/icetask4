package za.ac.iie.icetask4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.max

class InputTempScreen : AppCompatActivity() {

    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val maxTemperatures = IntArray(7)
    private val weatherConditions = Array(7) { "" }
    private var currentIndex = 0

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input_temp_screen)

        val dayPrompt: TextView = findViewById(R.id.dayPrompt)
        val tempInput: EditText = findViewById(R.id.temperatureInput)
        val saveButton: Button = findViewById(R.id.saveButton)
        val finishButton: Button = findViewById(R.id.resultButton)

        fun updatePrompt() {
            if (currentIndex < days.size) {
                dayPrompt.text = "Enter the temperature for ${days[currentIndex]}:"
                tempInput.text.clear()
            } else {
                dayPrompt.text = "All temperatures entered."
                tempInput.visibility = View.GONE
                saveButton.visibility = View.GONE
                finishButton.visibility = View.VISIBLE
            }
        }

        saveButton.setOnClickListener {
            val temp = tempInput.text.toString().toIntOrNull()
            if (temp != null) {
                maxTemperatures[currentIndex] = temp

                weatherConditions[currentIndex] = when {
                    temp >= 28 -> "Hot"
                    temp in 23..27 -> "Sunny"
                    temp in 18..22 -> "Cloudy"
                    temp in 13..17 -> "Rainy"
                    else -> "Cold"
                }

                currentIndex ++
                updatePrompt()
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        finishButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("UPDATED_TEMPS", maxTemperatures)
            setResult(RESULT_OK, resultIntent)
            gotomainscreen()

        }
        updatePrompt()
    }
    private fun gotomainscreen () {
        val mainScreen = Intent (this, MainActivity::class.java)
        mainScreen.putExtra("UPDATED_TEMPS", maxTemperatures)
        mainScreen.putExtra("WEATHER_CONDITIONS", weatherConditions)
        startActivity(mainScreen)
    }
}