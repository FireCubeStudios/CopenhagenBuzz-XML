package dk.itu.moapd.copenhagenbuzz.aybo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.core.view.WindowCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.models.Event
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityMainBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    // A set of private constants used in this class. (exercise 1)
    private lateinit var binding: ActivityMainBinding
    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var eventName: EditText
    private lateinit var eventLocation: EditText
    private lateinit var eventDate: EditText
    private lateinit var eventType: MaterialAutoCompleteTextView
    private lateinit var eventDescription: EditText
    private lateinit var addEventButton: FloatingActionButton

    // An instance of the `Event` class. (exercise 1)
    private val event: Event = Event(
        eventName = "",
        eventLocation = "",
        eventDate = LocalDateTime.now(),
        eventType = "",
        eventDescription = ""
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false) // exercise 1 code
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        topAppBar = binding.topAppBar
        eventName = binding.contentMain.editTextEventName
        eventLocation = binding.contentMain.editTextEventLocation
        eventDate = binding.contentMain.editTextEventDate
        eventType = binding.contentMain.autoCompleteEventType
        eventDescription = binding.contentMain.editTextEventDescription
        addEventButton = binding.contentMain.fabAddEvent

        // Listener for user interaction in the `Add Event` button.
        addEventButton.setOnClickListener {
            // Only execute the following code when the user fills all fields
            if (eventName.text.toString().isNotEmpty()
            && eventLocation.text.toString().isNotEmpty()
            && eventDate.text.toString().isNotEmpty()
            && eventType.hasSelection()
            && eventDescription.text.toString().isNotEmpty()) {
                // Update the object attributes.
                event.eventName = eventName.text.toString().trim()
                event.eventLocation = eventLocation.text.toString().trim()
                event.eventDate = LocalDateTime.parse(eventDate.text.toString().trim())
                event.eventType = eventType.text.toString().trim()
                event.eventDescription = eventDescription.text.toString().trim()
            }

            showMessage()
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.login -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_top_app_bar, menu)
        return true
    }

    // Show login or logout button depending on if is logged in or logged out in the top app bar menu
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.login).isVisible = !intent.getBooleanExtra("IsLoggedIn", false)
        menu.findItem(R.id.logout).isVisible = intent.getBooleanExtra("IsLoggedIn", false)
        return true
    }

    private fun showMessage() {
        Snackbar.make(binding.root, event.toString(), Snackbar.LENGTH_SHORT).show()
        Log.d(TAG, event.toString())
    }
}