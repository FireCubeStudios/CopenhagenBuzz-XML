package dk.itu.moapd.copenhagenbuzz.aybo.models

import java.time.LocalDateTime

data class Event(val eventName: String,
                 val eventLocation: String,
                 val eventDate: LocalDateTime,
                 val eventType: String,
                 val eventDescription: String)