package com.mruraza.cleanarchitecture.Presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mruraza.cleanarchitecture.Domain.UseCases.GetImageUseCase
import com.mruraza.cleanarchitecture.Domain.model.DomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class ImageViewModel @Inject constructor(private val getImageUseCase: GetImageUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")

    fun updateQuery(query: String) {
     _query.update { query }
    }

    init {
        viewModelScope.launch {
            _query.filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .debounce(1000)
                .collectLatest { query->
                    getImages(query)
                }
        }
    }

    private fun getImages(query: String) {
        getImageUseCase(query)
            .onStart {_uiState.update { it.copy(isLoading = true)  }}
            .onEach { result->
                if(result.isSuccess){
                    //Log.d("Imagess", "getImages: ${result.getOrThrow()}")
                    _uiState.update { UiState(data = result.getOrThrow()) }
                }else{
                    _uiState.update { UiState(error = result.exceptionOrNull()?.message.toString()) }
                }
            }.catch { error->
                _uiState.update { UiState(error = error.message.toString()) }
            }.launchIn(viewModelScope)
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<DomainModel>? = null
)