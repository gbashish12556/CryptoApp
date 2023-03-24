package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.presentation.model.CoinDetailState
import com.plcoding.cryptocurrencyappyt.domain.use_cases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(

    val getCoinUseCase: GetCoinUseCase,
    savedStateHandle:SavedStateHandle

):ViewModel(){

    private val _state = mutableStateOf(CoinDetailState())
    val state = _state

    init {

        savedStateHandle.get<String>(Constants.COIN_ID)?.let { coinId->

            getCoin(coinId)

        }

    }
    private fun getCoin(coinId:String) {


        viewModelScope.launch {

            getCoinUseCase(coinId = coinId).onEach {result->

                when(result){

                    is Resource.Success->{
                        _state.value = CoinDetailState(coin = result.data)
                    }

                    is Resource.Error->{

                        _state.value = CoinDetailState(error = result.message ?: "Something went wrong")
                    }

                    is Resource.Loading->{

                        _state.value = CoinDetailState(isLoading = true)

                    }
                }

            }

        }

    }


}