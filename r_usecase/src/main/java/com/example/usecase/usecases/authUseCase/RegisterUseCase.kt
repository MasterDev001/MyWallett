package com.example.usecase.usecases.authUseCase

import com.example.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
     operator fun invoke(name: String, email: String, password: String) =
        repository.registerWithEmail(name, email, password)
}