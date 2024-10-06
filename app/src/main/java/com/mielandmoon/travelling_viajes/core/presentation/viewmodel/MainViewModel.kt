package com.mielandmoon.travelling_viajes.core.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.core.domain.usecase.ClearUserDataStoreUseCase
import com.mielandmoon.travelling_viajes.core.domain.usecase.GetUserDataStoreUseCase
import com.mielandmoon.travelling_viajes.core.domain.usecase.SaveUserDataStoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserDataStoreUseCase: GetUserDataStoreUseCase,
    private val clearUserDataStoreUseCase: ClearUserDataStoreUseCase
) : ViewModel() {
    private val _userDataStore = MutableStateFlow<User?>(null)
    val userDataStore = _userDataStore.asStateFlow()

    init {
        fetchUserDataStore()
    }

    private fun fetchUserDataStore() {
        viewModelScope.launch {
            getUserDataStoreUseCase.execute().collect { user ->
                if (user.id != 0) {
                    _userDataStore.value = user
                } else {
                    Log.d("MainViewModel", "No user data found")
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            clearUserDataStoreUseCase.execute()
        }
    }
}