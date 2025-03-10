package dk.itu.moapd.copenhagenbuzz.aybo.ui.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.javafaker.Faker
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityMainBinding
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.FragmentTimelineBinding
import dk.itu.moapd.copenhagenbuzz.aybo.models.DummyModel
import dk.itu.moapd.copenhagenbuzz.aybo.ui.Adapters.EventAdapter
import java.time.LocalDateTime
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimelineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimelineFragment : Fragment() {
    private lateinit var binding: FragmentTimelineBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val faker = Faker()
        binding.apply {
            val data = ArrayList<DummyModel>()
            (1..10).forEach { it ->
                val address = faker.address()
                data.add(
                    DummyModel(
                        eventName = "Event name",
                        eventLocation = address.streetAddress(),
                        eventDescription = faker.lorem().paragraph(),
                        eventDate = "Fri, Jan 31 2025 - Sun, Feb 23 2025",
                        eventType = "Event Type",
                        eventPhoto = Uri.parse("https://picsum.photos/seed/$it/400/194")
                    ),
                )
            }
            // Define the list view adapter.
            val adapter = EventAdapter(requireContext(), R.layout.event_row_item, data)
            listView.adapter = adapter
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimelineBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimelineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimelineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}