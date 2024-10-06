package com.mielandmoon.travelling_viajes.auth.domain.usecase.validation

import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.TextError

class ValidateLastNameUseCase {
    fun execute(lastName: String): Result<Unit, TextError> {
        if (lastName.isBlank()) {
            return Result.Error(TextError.EMPTY)
        }
        return Result.Success(Unit)
    }
}