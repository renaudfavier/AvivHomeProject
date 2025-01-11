package com.example.avivhomeproject.property.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.avivhomeproject.PropertyDetailRoute
import com.example.avivhomeproject.property.domain.PropertyRepository
import com.example.avivhomeproject.property.presentation.detail.PropertyDetailUiModel as UiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val propertyRepository: PropertyRepository,
    private val mapper: DetailUiModelMapper,
): ViewModel() {

    private val propertyId = savedStateHandle.toRoute<PropertyDetailRoute>().id

    private val _uiState = MutableStateFlow<UiModel>(UiModel.Loading)
    val uiState = _uiState
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiModel.Loading
        )

    private fun loadData() = viewModelScope.launch {
        _uiState.value = UiModel.Loading
        propertyRepository.getProperty(propertyId).fold(
            onSuccess = { _uiState.value = UiModel.Content(mapper.map(it)) },
            onFailure = { _uiState.value = UiModel.Error(it.message ?: "no message") }
        )
    }
}
