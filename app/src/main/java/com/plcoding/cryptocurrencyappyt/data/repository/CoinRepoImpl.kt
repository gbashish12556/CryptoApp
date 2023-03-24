package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.models.Coin
import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(

    private val api:CoinPaprikaApi

): CoinRepository {
    override suspend fun getCoins() = flow {

        try {

            emit(Resource.Loading<List<Coin>>())
            val coins = api.getCoins().map { it.toCoin() }
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

    override suspend fun getCoinById(coinId: String) = flow {
        try {

            emit(Resource.Loading<CoinDetail>())
            val coin = api.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))

        } catch(e: HttpException) {

            emit(Resource.Error<CoinDetail>(message = e.localizedMessage ?: "An unexpected error occured"))

        } catch(e: IOException) {

            emit(Resource.Error<CoinDetail>(message = "Couldn't reach server. Check your internet connection."))

        }
    }
}