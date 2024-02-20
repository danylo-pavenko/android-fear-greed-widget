package com.depsoftware.fgindex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.depsoftware.fgindex.data.datasource.IFearGreedDataSource
import com.depsoftware.fgindex.data.datasource.RemoteFearGreedDataSource
import com.depsoftware.fgindex.domain.FearGreedIndexRepository
import com.depsoftware.fgindex.domain.IFearGreedIndexRepository
import com.depsoftware.fgindex.domain.model.FearGreedIndex
import com.depsoftware.fgindex.ui.screen.FearGreedIndexState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FearGreedIndexViewModel: ViewModel() {

    private val dataSource: IFearGreedDataSource = RemoteFearGreedDataSource()
    val repository: IFearGreedIndexRepository = FearGreedIndexRepository(dataSource)

    private val _uiState = MutableStateFlow<FearGreedIndexState>(FearGreedIndexState.Default)
    val uiState: StateFlow<FearGreedIndexState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.emit(FearGreedIndexState.Loading)
            val indexes = repository.getFearGreedIndex()
            val actual = indexes.first { it.type == FearGreedIndex.Type.ACTUAL }
            val yesterday = indexes.first { it.type == FearGreedIndex.Type.YESTERDAY }
            val lastWeek = indexes.first { it.type == FearGreedIndex.Type.LAST_WEEK }
            _uiState.emit(
                FearGreedIndexState.Success(
                    actual.value,
                    yesterday.value,
                    lastWeek.value,
                    actual.classification.nameResId,
                    yesterday.classification.nameResId,
                    lastWeek.classification.nameResId,
                )
            )
        }
    }
}
