package com.josemaeso.spacexrockets.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import com.josemaeso.spacexrockets.ui.Event

class ListViewModel(rocketInteractor: RocketInteractor) : ViewModel() {
    val rockets = rocketInteractor.getRockets().asLiveData()
    val selectedRocket = MutableLiveData<Event<Rocket>>()

    fun rocketSelected(rocket: Rocket) {
        selectedRocket.postValue(Event(rocket))
    }
}