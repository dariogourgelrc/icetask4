package za.ac.iie.icetask4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen)

        val btnGoToTemp: Button = findViewById(R.id.btn_go_to_temp)

        btnGoToTemp.setOnClickListener {
            goToMainScreen()
        }
    }
    private fun goToMainScreen () {
        val inputScreen = Intent (this, InputTempScreen::class.java)
        startActivity(inputScreen)
    }
}
