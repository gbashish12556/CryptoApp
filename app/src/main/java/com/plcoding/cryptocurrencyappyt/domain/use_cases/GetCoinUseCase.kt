package com.plcoding.cryptocurrencyappyt.domain.use_cases

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId:String) = flow {

        try{

            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail();
            emit(Resource.Success(coin))

        }catch (e: HttpException){

            emit(Resource.Error(message = e.localizedMessage?:"http exceptions"))

        }catch (e: IOException){

            emit(Resource.Error(message = e.localizedMessage?:"IO exceptions"))

        }

    }
}