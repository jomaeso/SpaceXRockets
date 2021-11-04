package com.josemaeso.spacexrockets.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    rocketInteractor: RocketInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val rocket = rocketInteractor.getRocket(savedStateHandle["rocketId"]!!).asLiveData()
}