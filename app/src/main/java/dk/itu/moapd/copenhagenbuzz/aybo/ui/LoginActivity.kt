package dk.itu.moapd.copenhagenbuzz.aybo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginButton: Button
    private lateinit var guestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater) // ViewBinding code
        setContentView(binding.root) // ViewBinding code
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bind the buttons with ViewBinding
        loginButton = binding.buttonLogin
        guestButton = binding.buttonGuest

        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("IsLoggedIn", true)
            startActivity(intent)
        }
        guestButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("IsLoggedIn", false)
            startActivity(intent)
        }
    }
}