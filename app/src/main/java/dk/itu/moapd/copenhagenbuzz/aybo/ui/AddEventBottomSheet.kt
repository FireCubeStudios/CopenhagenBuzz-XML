package dk.itu.moapd.copenhagenbuzz.aybo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityMainBinding
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.AddEventBottomSheetBinding
import dk.itu.moapd.copenhagenbuzz.aybo.models.Event
import java.time.LocalDateTime

class AddEventBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: AddEventBottomSheetBinding
    companion object { // TAG used when showing sheet AddEventBottomSheet().show(supportFragmentManager, AddEventBottomSheet.TAG)
        val TAG = AddEventBottomSheet::class.qualifiedName
    }

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = AddEventBottomSheetBinding.inflate(layoutInflater)
        eventName = binding.editTextEventName
        eventLocation = binding.editTextEventLocation
        eventDate = binding.editTextEventDate
        eventType = binding.autoCompleteEventType
        eventDescription = binding.editTextEventDescription
        addEventButton = binding.fabAddEvent

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
        return binding.root
    }

    private fun showMessage() {
        Snackbar.make(binding.root, event.toString(), Snackbar.LENGTH_SHORT).show()
    }
}