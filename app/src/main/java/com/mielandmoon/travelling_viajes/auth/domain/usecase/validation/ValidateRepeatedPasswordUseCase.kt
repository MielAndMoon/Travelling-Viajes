package com.mielandmoon.travelling_viajes.auth.domain.usecase.validation

import com.mielandmoon.travelling_viajes.common.domain.model.RepeatedPasswordError
import com.mielandmoon.travelling_viajes.common.domain.model.Result

class ValidateRepeatedPasswordUseCase {
    fun execute(password: String, repeatedPassword: String): Result<Unit, RepeatedPasswordError> {
        if (password != repeatedPassword) {
            return Result.Error(RepeatedPasswordError.SAME)

        }
        return Result.Success(Unit)
    }
}