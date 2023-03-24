package com.plcoding.cryptocurrencyappyt.presentation.model

import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail


data class CoinDetailState(

    val isLoading:Boolean = false,
    val error:String?  = null,
    val coin: CoinDetail? = null

)