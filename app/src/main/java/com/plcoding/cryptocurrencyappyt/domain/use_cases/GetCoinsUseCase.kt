package com.plcoding.cryptocurrencyappyt.domain.use_cases

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.models.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) : BaseCase<Flow<Resource<List<Coin>>>, Unit>() {

    override suspend fun invoke(parameters: Unit?) = flow{

        try {

            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins()
            emit(Resource.Success<List<Coin>>(coins))

        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Coin>>(
                    message = e.localizedMessage ?: "An unexpected error occured"
                )
            )

        } catch (e: IOException) {

            emit(Resource.Error<List<Coin>>(message = "Couldn't reach server. Check your internet connection."))

        }
    }
}