package com.josemaeso.spacexrockets.ui.list

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.databinding.RocketListItemBinding
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

class RocketListViewHolder(
    private val binding: RocketListItemBinding,
    private val listener: RocketListAdapter.RocketListItemListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(rocket: Rocket) {
        binding.rocketName.text = rocket.name
        binding.rocketCompany.text = rocket.company
        binding.rocketCountry.text = rocket.country

        val statusColor = ContextCompat.getColor(
            itemView.context,
            if (rocket.active) R.color.rocket_active else R.color.rocket_inactive
        )
        ImageViewCompat.setImageTintList(
            binding.rocketStatus,
            ColorStateList.valueOf(statusColor)
        )

        itemView.setOnClickListener {
            listener.onRocketItemClick(rocket)
        }
    }
}