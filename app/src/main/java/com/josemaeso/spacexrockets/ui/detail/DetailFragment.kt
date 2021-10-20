package com.josemaeso.spacexrockets.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.SpaceXRocketsApplication
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
            }
        }
    }

}