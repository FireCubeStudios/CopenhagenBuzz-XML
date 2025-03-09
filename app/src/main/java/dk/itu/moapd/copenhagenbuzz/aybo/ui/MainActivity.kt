package dk.itu.moapd.copenhagenbuzz.aybo.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.core.view.WindowCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dk.itu.moapd.copenhagenbuzz.aybo.models.Event
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityMainBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    // A set of private constants used in this class. (exercise 1)
    private lateinit var binding: ActivityMainBinding
    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }

    // GUI variables. (exercise 1)
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

                // Write in the `Logcat` system.
                showMessage()
            }
        }
    }

    private fun showMessage() {
        Log.d(TAG, event.toString())
    }
}