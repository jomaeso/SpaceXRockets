package com.josemaeso.spacexrockets.ui.list

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.josemaeso.spacexrockets.R
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import kotlinx.android.synthetic.main.rocket_list_item.view.*

class RocketListViewHolder(
    itemView: View,
    private val listener: RocketListAdapter.RocketListItemListener
) : RecyclerView.ViewHolder(itemView) {
    fun bind(rocket: Rocket) {
        itemView.rocket_name.text = rocket.name
        itemView.rocket_company.text = rocket.company
        itemView.rocket_country.text = rocket.country

        val statusColor = ContextCompat.getColor(
            itemView.context,
            if (rocket.active) R.color.rocket_active else R.color.rocket_inactive
        )
        ImageViewCompat.setImageTintList(
            itemView.rocket_status,
            ColorStateList.valueOf(statusColor)
        )

        itemView.setOnClickListener {
            listener.onRocketItemClick(rocket)
        }
    }
}