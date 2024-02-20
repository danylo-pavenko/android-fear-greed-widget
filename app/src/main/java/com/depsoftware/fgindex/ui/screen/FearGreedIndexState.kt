package com.depsoftware.fgindex.ui.screen

import androidx.annotation.StringRes
import com.depsoftware.fgindex.R

sealed class FearGreedIndexState {
    data class Success(
        val actualFearGreedIndex: Int = 0,
        val yesterdayFearGreedIndex: Int = 0,
        val lastWeekFearGreedIndex: Int = 0,
        @StringRes
        val actualFearGreedClassification: Int = R.string.label_index_classification_none,
        @StringRes
        val yesterdayFearGreedClassification: Int = R.string.label_index_classification_none,
        @StringRes
        val lastWeekFearGreedClassification: Int = R.string.label_index_classification_none,
    ): FearGreedIndexState()

    data object Default: FearGreedIndexState()
    data object Loading: FearGreedIndexState()
    data object Error: FearGreedIndexState()
}
