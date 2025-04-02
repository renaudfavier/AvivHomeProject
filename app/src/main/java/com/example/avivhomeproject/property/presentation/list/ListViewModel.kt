package com.example.avivhomeproject.property.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avivhomeproject.property.domain.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.avivhomeproject.property.presentation.list.PropertyListUiModel as UiModel

@HiltViewModel
class ListViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val mapper: ListUiModelMapper,
): ViewModel() {

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
        propertyRepository.getListedProperties().fold(
            onSuccess = { _uiState.value = UiModel.Content(mapper.map(it)) },
            onFailure = { _uiState.value = UiModel.Error(it.message ?: "unknown error") }
        )
    }
}
