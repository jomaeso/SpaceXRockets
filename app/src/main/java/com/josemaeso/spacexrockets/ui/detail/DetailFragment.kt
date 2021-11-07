package com.josemaeso.spacexrockets.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.databinding.DetailFragmentBinding
import com.josemaeso.spacexrockets.ui.loader.UIImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    @Inject
    lateinit var imageLoader: UIImageLoader
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContentView()
    }

    private fun setContentView() {
        viewModel.rocket.observe(viewLifecycleOwner) {
            it?.let { rocket ->
                binding.rocketName.text = rocket.name
                binding.rocketDescription.text = rocket.description
                binding.rocketCompany.text = rocket.company
                binding.rocketCountry.text = rocket.country
                binding.rocketStatus.text =
                    if (rocket.active) getString(R.string.rocket_status_active) else getString(
                        R.string.rocket_status_inactive
                    )
                binding.rocketStatus.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (rocket.active) R.color.rocket_active else R.color.rocket_inactive
                    )
                )
                if (rocket.images.isNotEmpty()) {
                    imageLoader.loadImage(
                        rocket.images.first(),
                        binding.rocketImage,
                        R.drawable.rocket_place_holder
                    )
                }
            }
        }
    }

}