package com.example.r_usecase.usecases.currencyUseCase

import MyCurrency
import com.example.repository.CurrencyRepository
import javax.inject.Inject

class AddCurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {

suspend operator fun invoke(currency:MyCurrency)=currencyRepository.addCurrency(currency)
}