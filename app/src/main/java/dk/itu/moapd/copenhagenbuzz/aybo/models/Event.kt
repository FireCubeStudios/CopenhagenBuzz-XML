package dk.itu.moapd.copenhagenbuzz.aybo.models

import java.time.LocalDateTime

data class Event(var eventName: String,
                 var eventLocation: String,
                 var eventDate: LocalDateTime,
                 var eventType: String,
                 var eventDescription: String)