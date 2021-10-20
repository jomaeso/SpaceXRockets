package com.josemaeso.spacexrockets.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor

class DetailViewModelFactory(private val rocketId: String, private val rocketInteractor: RocketInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(String::class.java, RocketInteractor::class.java).newInstance(rocketId, rocketInteractor)
    }
}