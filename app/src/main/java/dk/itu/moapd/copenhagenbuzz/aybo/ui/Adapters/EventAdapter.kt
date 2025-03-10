package dk.itu.moapd.copenhagenbuzz.aybo.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.models.DummyModel

class EventAdapter(private val context: Context, private var resource: Int,
                   data:
                   List<DummyModel>) :
    ArrayAdapter<DummyModel>(context, R.layout.event_row_item, data) {

    private class ViewHolder(view: View) {
        val eventPhoto: ImageView = view.findViewById(R.id.event_photo)
        val eventNameText: TextView = view.findViewById(R.id.event_name_text)
        val eventTypeText: TextView = view.findViewById(R.id.event_type_text)
        val eventLocationText: TextView = view.findViewById(R.id.event_location_text)
        val eventDateText: TextView = view.findViewById(R.id.event_date_text)
        val eventDescriptionText: TextView = view.findViewById(R.id.event_description_text)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
        val viewHolder = (view.tag as? ViewHolder) ?: ViewHolder(view)
        // Populate the view holder with the selected `Dummy` data.
        getItem(position)?.let { dummy ->
            populateViewHolder(viewHolder, dummy)
        }
        view.tag = viewHolder
        return view
    }

    private fun populateViewHolder(viewHolder: ViewHolder, dummy: DummyModel) {
        with(viewHolder) {
            // Fill out the Material Design card.
            Picasso.get().load(dummy.eventPhoto).into(eventPhoto)
            eventNameText.text = dummy.eventName
            eventTypeText.text = dummy.eventType
            eventLocationText.text = dummy.eventLocation
            eventDateText.text = dummy.eventDate
            eventDescriptionText.text = dummy.eventDescription
        }
    }
}