package com.mielandmoon.travelling_viajes.auth.domain.usecase.validation

import android.util.Patterns
import com.mielandmoon.travelling_viajes.common.domain.model.EmailError
import com.mielandmoon.travelling_viajes.common.domain.model.Result

class ValidateEmailUseCase {
    fun execute(email: String): Result<Unit, EmailError> {
        if (email.isBlank()) {
            return Result.Error(EmailError.EMPTY)
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.Error(EmailError.INVALID)

        }
        return Result.Success(Unit)
    }
}