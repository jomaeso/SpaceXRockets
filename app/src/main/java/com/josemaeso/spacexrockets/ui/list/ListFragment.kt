package com.josemaeso.spacexrockets.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.SpaceXRocketsApplication
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(), RocketListAdapter.RocketListItemListener {

    private val viewModel: ListViewModel by viewModels { ListViewModelFactory((activity?.application as SpaceXRocketsApplication).rocketInteractor) }
    private val rocketAdapter = RocketListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rocket_list.adapter = rocketAdapter
        rocket_list.layoutManager = LinearLayoutManager(requireContext())

        viewModel.rockets.observe(viewLifecycleOwner) {
            rocketAdapter.submitList(it)
        }

        viewModel.selectedRocket.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { rocket ->
                val action =
                    ListFragmentDirections.actionListFragmentToDetailFragment(rocket.rocketId, rocket.name)
                findNavController().navigate(action)
            }
        }
    }

    override fun onRocketItemClick(rocket: Rocket) {
        viewModel.rocketSelected(rocket)
    }

}