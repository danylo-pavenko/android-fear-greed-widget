package com.depsoftware.fgindex.domain.model

import androidx.annotation.StringRes
import com.depsoftware.fgindex.R
import com.depsoftware.fgindex.data.FearGreedIndexResponse

data class FearGreedIndex(
    val value: Int,
    val classification: Classification,
    val type: Type
) {
    enum class Type {
        ACTUAL, YESTERDAY, LAST_WEEK
    }

    enum class Classification(@StringRes val nameResId: Int) {
        FEAR(R.string.label_index_classification_fear),
        EXTREME_FEAR(R.string.label_index_classification_fear_extreme),
        GREED(R.string.label_index_classification_greed),
        EXTREME_GREED(R.string.label_index_classification_greed_extreme),
    }
}

fun FearGreedIndexResponse.map(type: FearGreedIndex.Type): FearGreedIndex {
    return FearGreedIndex(
        this.value,
        FearGreedIndex.Classification.valueOf(this.value_classification.uppercase().replace(" ", "_")),
        type
    )
}
