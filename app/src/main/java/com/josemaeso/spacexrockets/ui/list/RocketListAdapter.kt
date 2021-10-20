package com.josemaeso.spacexrockets.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

class RocketListAdapter(private val listener: RocketListItemListener): ListAdapter<Rocket, RocketListViewHolder>(DiffCallback()) {

    interface RocketListItemListener {
        fun onRocketItemClick(rocket: Rocket)
    }

    private class DiffCallback : DiffUtil.ItemCallback<Rocket>() {
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket) =
            oldItem.rocketId == newItem.rocketId

        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket) =
            true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketListViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.rocket_list_item, parent, false) as View
        return RocketListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RocketListViewHolder, position: Int) {
        val rocket = currentList[position]
        holder.bind(rocket)
    }
}