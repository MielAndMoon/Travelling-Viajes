package com.mielandmoon.travelling_viajes.destination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielandmoon.travelling_viajes.common.domain.model.DestinationError
import com.mielandmoon.travelling_viajes.destination.domain.state.FavoriteUiState
import com.mielandmoon.travelling_viajes.destination.domain.usecase.GetFavoriteDestinationsByUser
import kotlinx.coroutines.flow.MutableStateFlow
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.core.domain.usecase.GetUserDataStoreUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.AddToFavoriteUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteDestinationsByUser: GetFavoriteDestinationsByUser,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteUiState(emptyList()))
    val state = _state.asStateFlow()

    fun fetchFavoriteDestinations(userId: Int) {
        viewModelScope.launch {
            getFavoriteDestinationsByUser(userId).collect { result ->
                Log.d("FavoriteViewModel", "fetchFavoriteDestinations: $result")
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            favoriteDestinations = result.data,
                        )
                    }

                    is Result.Error -> {
                        when (result.error) {
                            DestinationError.SERVER_ERROR -> {
                                _state.value = _state.value.copy(
                                    favoriteDestinations = emptyList(),
                                )
                            }

                            DestinationError.NETWORK_ERROR -> {
                                _state.value = _state.value.copy(
                                    favoriteDestinations = emptyList(),
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}