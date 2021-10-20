package com.josemaeso.spacexrockets.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor

class DetailViewModel(rocketId: String, rocketInteractor: RocketInteractor) : ViewModel() {
    val rocket = rocketInteractor.getRocket(rocketId).asLiveData()
}