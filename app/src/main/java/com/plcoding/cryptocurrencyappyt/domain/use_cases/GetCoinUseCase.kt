package com.plcoding.cryptocurrencyappyt.domain.use_cases

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
):BaseCase<Flow<Resource<CoinDetail>>, String>() {
    override suspend fun invoke(coinId: String?)= flow {
        try {

            emit(Resource.Loading<CoinDetail>())
            coinId?.let { id ->
                val coin = repository.getCoinById(id)
                emit(Resource.Success<CoinDetail>(coin))
            }

        } catch(e: HttpException) {

            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "An unexpected error occured"))

        } catch(e: IOException) {

            emit(Resource.Error<CoinDetail>(message = "Couldn't reach server. Check your internet connection."))

        }
    }

}