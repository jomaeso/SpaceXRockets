package com.josemaeso.spacexrockets.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.SpaceXRocketsApplication
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(
            args.rocketId,
            (activity?.application as SpaceXRocketsApplication).rocketInteractor
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.rocket.observe(viewLifecycleOwner) {
            it?.let { rocket ->
                rocket_name.text = rocket.name
                rocket_description.text = rocket.description
                rocket_company.text = rocket.company
                rocket_country.text = rocket.country
                rocket_status.text =
                    if (rocket.active) getString(R.string.rocket_status_active) else getString(
                        R.string.rocket_status_inactive
                    )
                rocket_status.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        if (rocket.active) R.color.rocket_active else R.color.rocket_inactive
                    )
                )
                if (rocket.images.isNotEmpty()) {
                    Picasso.get().load(rocket.images.first())
                        .placeholder(R.drawable.rocket_place_holder).fit().centerCrop()
                        .into(rocket_image)
                }
            }
        }
    }

}