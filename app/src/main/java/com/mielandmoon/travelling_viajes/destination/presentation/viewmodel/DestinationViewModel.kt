package com.mielandmoon.travelling_viajes.destination.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielandmoon.travelling_viajes.common.domain.model.DestinationError
import com.mielandmoon.travelling_viajes.destination.domain.state.DestinationUiState
import com.mielandmoon.travelling_viajes.destination.domain.usecase.GetDestinationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.mielandmoon.travelling_viajes.common.domain.model.Result

class DestinationViewModel(
    private val getDestinationsUseCase: GetDestinationsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(DestinationUiState(emptyList()))
    val state = _state.asStateFlow()

    init {
        fetchDestinations()
    }

    private fun fetchDestinations() {
        viewModelScope.launch {
            getDestinationsUseCase.execute().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            destinations = result.data,
                        )
                    }

                    is Result.Error -> {
                        when (result.error) {
                            DestinationError.SERVER_ERROR -> {
                                _state.value = _state.value.copy(
                                    destinations = emptyList(),
                                )
                            }

                            DestinationError.NETWORK_ERROR -> {
                                _state.value = _state.value.copy(
                                    destinations = emptyList(),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}