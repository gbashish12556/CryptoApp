package com.plcoding.cryptocurrencyappyt.presentation.model

import com.plcoding.cryptocurrencyappyt.domain.models.Coin

data class CoinListState(

    val isLoading:Boolean = false,
    val error:String?  = null,
    val coins:List<Coin>? = emptyList()

)