package dk.itu.moapd.copenhagenbuzz.aybo.models

import java.time.LocalDateTime

/**
 * Represents an event with details such as name, location, date, type, and description.
 *
 * @property eventName The name of the event.
 * @property eventLocation The location where the event takes place.
 * @property eventDate The date and time of the event.
 * @property eventType The type of the event.
 * @property eventDescription A detailed description of the event.
 */
data class Event(var eventName: String,
                 var eventLocation: String,
                 var eventDate: LocalDateTime,
                 var eventType: String,
                 var eventDescription: String)