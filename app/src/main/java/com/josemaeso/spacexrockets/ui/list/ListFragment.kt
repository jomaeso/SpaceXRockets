package com.josemaeso.spacexrockets.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.josemaeso.spacexrockets.databinding.ListFragmentBinding
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), RocketListAdapter.RocketListItemListener {

    private val viewModel: ListViewModel by viewModels()
    private val rocketAdapter = RocketListAdapter(this)
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rocketList.adapter = rocketAdapter
        binding.rocketList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.rockets.observe(viewLifecycleOwner) {
            rocketAdapter.submitList(it)
        }

        viewModel.selectedRocket.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { rocket ->
                val action =
                    ListFragmentDirections.actionListFragmentToDetailFragment(
                        rocket.rocketId,
                        rocket.name
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun onRocketItemClick(rocket: Rocket) {
        viewModel.rocketSelected(rocket)
    }

}