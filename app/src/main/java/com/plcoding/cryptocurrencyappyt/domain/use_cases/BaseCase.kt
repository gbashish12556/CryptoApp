package com.plcoding.cryptocurrencyappyt.domain.use_cases

abstract class BaseCase<out Result,in Params> {

     abstract suspend operator fun invoke(parameters: Params? = null): Result
}