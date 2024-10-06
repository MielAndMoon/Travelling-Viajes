package com.mielandmoon.travelling_viajes.auth.domain.usecase.validation

import com.mielandmoon.travelling_viajes.common.domain.model.PasswordError
import com.mielandmoon.travelling_viajes.common.domain.model.Result

class ValidatePasswordUseCase {
    fun execute(password: String): Result<Unit, PasswordError> {
        if (password.length < 8) {
            return Result.Error(PasswordError.SHORT)
        }
        return Result.Success(Unit)
    }
}