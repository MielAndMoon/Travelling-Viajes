package com.mielandmoon.travelling_viajes.auth.domain.usecase.validation

import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.TextError

class ValidateFirstNameUseCase {
    fun execute(firstName: String): Result<Unit, TextError> {
        if (firstName.isBlank()) {
            return Result.Error(TextError.EMPTY)

        }
        return Result.Success(Unit)
    }
}