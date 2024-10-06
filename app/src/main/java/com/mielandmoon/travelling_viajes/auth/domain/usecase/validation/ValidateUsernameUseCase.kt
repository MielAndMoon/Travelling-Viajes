package com.mielandmoon.travelling_viajes.auth.domain.usecase.validation

import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.TextError

class ValidateUsernameUseCase {
    fun execute(username: String): Result<Unit, TextError> {
        if (username.isBlank()) {
            return Result.Error(TextError.EMPTY)
        }
        // TODO: check if the username is already taken
        return Result.Success(Unit)
    }
}