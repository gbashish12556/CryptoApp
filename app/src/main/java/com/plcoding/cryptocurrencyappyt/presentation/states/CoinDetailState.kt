package com.plcoding.cryptocurrencyappyt.presentation.states

import com.plcoding.cryptocurrencyappyt.data.models.CoinDetail


data class CoinDetailState(

    val isLoading:Boolean? = false,
    val error:String?  = null,
    val coin: CoinDetail? = null

)