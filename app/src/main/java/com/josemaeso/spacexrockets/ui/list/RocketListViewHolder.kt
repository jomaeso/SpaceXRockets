package com.josemaeso.spacexrockets.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import kotlinx.android.synthetic.main.rocket_list_item.view.*

class RocketListViewHolder(itemView: View, private val listener: RocketListAdapter.RocketListItemListener) : RecyclerView.ViewHolder(itemView) {
    fun bind(rocket: Rocket) {
        itemView.rocket_name.text = rocket.name
        itemView.rocket_description.text = rocket.description

        itemView.setOnClickListener {
            listener.onRocketItemClick(rocket)
        }
    }
}