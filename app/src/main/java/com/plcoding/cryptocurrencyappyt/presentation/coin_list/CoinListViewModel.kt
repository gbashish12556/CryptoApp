package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.presentation.model.CoinListState
import com.plcoding.cryptocurrencyappyt.domain.use_cases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    val getCoinsUseCase: GetCoinsUseCase
):ViewModel(){

    private val _state = mutableStateOf(CoinListState())
    val state = _state

    init {
        Log.d("AshishGupta","00");
        getCoins()
    }

    private fun getCoins() {


        viewModelScope.launch {
            val coins  = getCoinsUseCase()
            coins.collect {result->
                when(result){
                    is Resource.Success->{
                        _state.value = CoinListState(coins = result.data?: emptyList())
                    }
                    is Resource.Error->{
                        _state.value = CoinListState(error = result.message?:"Something went wrong")
                    }
                    is Resource.Loading->{
                        _state.value = CoinListState(isLoading = true)
                    }
                }
            }
        }
    }


}