package com.josemaeso.spacexrockets.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor

class ListViewModelFactory(private val rocketInteractor: RocketInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RocketInteractor::class.java).newInstance(rocketInteractor)
    }
}