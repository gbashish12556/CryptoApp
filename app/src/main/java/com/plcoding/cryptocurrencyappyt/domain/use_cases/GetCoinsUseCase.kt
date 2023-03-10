package com.plcoding.cryptocurrencyappyt.domain.use_cases

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.data.models.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke() = flow {

        try{

            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() };
            emit(Resource.Success(coins))

        }catch (e:HttpException){

            emit(Resource.Error(message = e.localizedMessage?:"http exceptions"))

        }catch (e:IOException){

            emit(Resource.Error(message = e.localizedMessage?:"IO exceptions"))

        }

    }
}