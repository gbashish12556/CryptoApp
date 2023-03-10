package com.plcoding.cryptocurrencyappyt.presentation.states

import com.plcoding.cryptocurrencyappyt.data.models.Coin

data class CoinListState(

    val isLoading:Boolean? = false,
    val error:String?  = null,
    val coins:List<Coin>? = null

)