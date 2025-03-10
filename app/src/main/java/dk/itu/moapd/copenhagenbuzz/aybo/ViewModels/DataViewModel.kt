package dk.itu.moapd.copenhagenbuzz.aybo.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.itu.moapd.copenhagenbuzz.aybo.models.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

// NOTE: The viewmodel does not contain "faker" data loading, i kept that in TimelineFragment
// The reason for this is the fake data is temporary anyway, the viewmodel still containd the exercise code
class DataViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val EVENTS_KEY = "events_list"
    }

    // MutableLiveData for storing event list
    private val _events = MutableLiveData<List<Event>>()

    // Public LiveData to be observed by the UI
    val events: LiveData<List<Event>> get() = _events

    /**
     * Fetches a list of events asynchronously using coroutines.
     */
    fun fetchEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            val eventList = generateEvents(10) // 10 events placeholder
            _events.postValue(eventList)
        }
    }

    /**
     * Generates a sample list of events.
     */
    private fun generateEvents(size: Int): List<Event> {
        return List(size) { index ->
            Event("Event Name", "Event Location", LocalDateTime.now(), "Event Type", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        }
    }
}