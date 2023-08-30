package com.hakanninc.wordhive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakanninc.wordhive.domain.use_case.GetTdkMeanUseCase
import com.hakanninc.wordhive.state.WordsState
import com.hakanninc.wordhive.util.Resource
import com.hakanninc.wordhive.util.capitalizeFirstLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TdkViewModel @Inject constructor(
    private val getTdkMeanUseCase: GetTdkMeanUseCase
) : ViewModel(){

    private val _state = MutableLiveData<WordsState>()
    val state: LiveData<WordsState>
        get() = _state

    private val _searchName = MutableLiveData<String>()
    val searchName: LiveData<String>
        get() = _searchName

    private var job: Job? = null

    init {
        getWordMean("")
    }

    fun getWordMean(searchQuery:String){
        _searchName.value = searchQuery.capitalizeFirstLetter()
        job?.cancel()

        job = getTdkMeanUseCase.executeGetTdkMean(searchQuery).onEach {

            when(it){
                is Resource.Success ->{
                    _state.value = WordsState(mean = it.data)
                }
                is Resource.Loading ->{
                    _state.value = _state.value?.copy(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = WordsState(error = "Error")
                }
            }
        }.launchIn(viewModelScope)
        }
    }












