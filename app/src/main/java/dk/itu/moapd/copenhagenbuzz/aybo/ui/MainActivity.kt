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
    private lateinit var binding: ActivityMainBinding
    // A set of private constants used in this class. (exercise 1)
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

    // TODO: Implement the missing GUI variables

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

        // Exercise 1 boilerplate code:
        eventName = findViewById(R.id.text_field_event_name)
        eventLocation = findViewById(R.id.text_field_event_location)
        eventDate = findViewById(R.id.text_field_event_date)
        eventType = findViewById(R.id.text_field_event_type)
        eventDescription = findViewById(R.id.text_field_event_description)
        addEventButton = findViewById(R.id.fab_add_event)

        // Listener for user interaction in the `Add Event` button.
        addEventButton.setOnClickListener {
            // Only execute the following code when the user fills all `EditText`.
            if (eventName.text.toString().isNotEmpty()
            && eventLocation.text.toString().isNotEmpty()
            && eventDate.text.toString().isNotEmpty()
            && eventType.hasSelection()
            && eventDescription.text.toString().isNotEmpty()) {
                // Update the object attributes.
                event.setEventName(
                    eventName.text.toString().trim()
                )
                event.setEventLocation(
                    eventLocation.text.toString().trim()
                )
                event.setEventDate(
                    LocalDateTime.parse(eventDate.text.toString().trim())
                )
                event.setEventType(
                    eventType.text.toString().trim()
                )
                event.setEventDescription(
                    eventDescription.text.toString().trim()
                )
                // Write in the `Logcat` system.
                showMessage()
            }
        }
    }

    private fun showMessage() {
        Log.d(TAG, event.toString())
    }
}