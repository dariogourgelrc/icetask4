package za.ac.iie.icetask4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val resultTextView: TextView = findViewById(R.id.resultTextView)

        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val maxTemperatures = intent.getIntArrayExtra("UPDATED_TEMPS") ?: intArrayOf(0, 0, 0, 0, 0, 0, 0)
        val weatherConditions = intent.getStringArrayExtra("WEATHER_CONDITIONS") ?: arrayOf("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A")
        val gobackBtn: Button = findViewById(R.id.backButton)

        val builder = StringBuilder()
        var sum = 0

        for (i in days.indices) {
            builder.append("${days[i]}: ${maxTemperatures[i]}°C - ${weatherConditions[i]}\n")
            sum += maxTemperatures[i]
        }

        val average = sum / maxTemperatures.size
        builder.append("\nAverage Temperature: $average°C")

        resultTextView.text = builder.toString()

        gobackBtn.setOnClickListener {
            goToWelcomeScreen()
        }
    }
    private fun goToWelcomeScreen () {
        val welcomeScreen = Intent (this, WelcomeScreen::class.java)
        startActivity(welcomeScreen)
    }
}