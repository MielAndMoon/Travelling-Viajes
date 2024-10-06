package com.mielandmoon.travelling_viajes.destination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielandmoon.travelling_viajes.common.domain.model.DestinationError
import com.mielandmoon.travelling_viajes.destination.domain.state.DestinationDetailUiState
import com.mielandmoon.travelling_viajes.destination.domain.usecase.GetDestinationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.destination.domain.usecase.AddToFavoriteUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.DeleteFavoriteUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.IsDestinationFavoriteUseCase

class DestinationDetailViewModel(
    private val getDestinationUseCase: GetDestinationUseCase,
    private val isDestinationFavoriteUseCase: IsDestinationFavoriteUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DestinationDetailUiState())
    val state = _state.asStateFlow()

    fun addToFavorite(destinationId: Int, userId: Int) {
        viewModelScope.launch {
            when (val result = addToFavoriteUseCase.execute(destinationId, userId)) {
                is Result.Error -> {
                    when (result.error) {
                        DestinationError.SERVER_ERROR -> {
                            _state.value = _state.value.copy(
                                isFavorite = false
                            )
                        }

                        DestinationError.NETWORK_ERROR -> {
                            _state.value = _state.value.copy(
                                isFavorite = false
                            )
                        }
                    }
                }

                is Result.Success -> {
                    _state.value = _state.value.copy(
                        isFavorite = true
                    )
                }
            }
        }
    }

    fun isFavorite(destinationId: Int, userId: Int) {
        viewModelScope.launch {
            when (val result = isDestinationFavoriteUseCase.execute(destinationId, userId)) {
                is Result.Error -> {
                    when (result.error) {
                        DestinationError.SERVER_ERROR -> {
                            _state.value = _state.value.copy(
                                isFavorite = false
                            )
                        }

                        DestinationError.NETWORK_ERROR -> {
                            _state.value = _state.value.copy(
                                isFavorite = false
                            )
                        }
                    }
                }

                is Result.Success -> {
                    _state.value = _state.value.copy(
                        isFavorite = result.data
                    )
                }
            }
        }
    }

    fun fetchDestination(id: Int) {
        viewModelScope.launch {
            when (val result = getDestinationUseCase.execute(id)) {
                is Result.Error -> {
                    when (result.error) {
                        DestinationError.SERVER_ERROR -> {
                            _state.value = _state.value.copy(
                                destination = null
                            )
                        }

                        DestinationError.NETWORK_ERROR -> {
                            _state.value = _state.value.copy(
                                destination = null
                            )
                        }
                    }
                }

                is Result.Success -> {
                    _state.value = _state.value.copy(
                        destination = result.data
                    )
                }
            }
        }
    }

    fun deleteFavorite(destinationId: Int, userId: Int) {
        viewModelScope.launch {
            when (val result = deleteFavoriteUseCase.execute(destinationId, userId)) {
                is Result.Error -> {
                    when (result.error) {
                        DestinationError.SERVER_ERROR -> {
                            Log.d("DestinationDetailViewModel", "deleteFavorite: $result")
                        }

                        DestinationError.NETWORK_ERROR -> {
                            Log.d("DestinationDetailViewModel", "deleteFavorite: $result")
                        }
                    }
                }

                is Result.Success -> {
                    _state.value = _state.value.copy(
                        isFavorite = false
                    )
                }
            }
        }
    }
}