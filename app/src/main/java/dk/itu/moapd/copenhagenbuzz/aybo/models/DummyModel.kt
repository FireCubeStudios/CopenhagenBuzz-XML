package dk.itu.moapd.copenhagenbuzz.aybo.models

import android.net.Uri
import java.time.LocalDateTime

data class DummyModel(var eventName: String,
                      var eventLocation: String,
                      var eventDate: String,
                      var eventType: String,
                      var eventPhoto: Uri,
                      var eventDescription: String)