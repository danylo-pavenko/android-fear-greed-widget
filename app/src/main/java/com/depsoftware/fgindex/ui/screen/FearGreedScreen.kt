package com.depsoftware.fgindex.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.depsoftware.fgindex.R
import com.depsoftware.fgindex.ui.theme.FearGreedIndexWidgetTheme
import com.depsoftware.fgindex.viewmodel.FearGreedIndexViewModel

@Composable
fun FearGreedScreen(viewModel: FearGreedIndexViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Text(text = stringResource(id = R.string.title_actual_fear_greed))
        }
        Row {
            val value = when (uiState) {
                FearGreedIndexState.Default -> stringResource(id = R.string.label_state_loading)
                FearGreedIndexState.Error -> stringResource(id = R.string.label_state_failed)
                FearGreedIndexState.Loading -> stringResource(id = R.string.label_state_loading)
                is FearGreedIndexState.Success -> "${(uiState as FearGreedIndexState.Success).actualFearGreedIndex}%"
            }
            val classification = if (uiState is FearGreedIndexState.Success) {
                stringResource(id = (uiState as FearGreedIndexState.Success).actualFearGreedClassification)
            } else {
                stringResource(id = R.string.label_index_classification_none)
            }
            Column(Modifier.fillMaxWidth(fraction = 0.5f)) {
                Text(
                    text = value,
                    modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Column(Modifier.fillMaxWidth(fraction = 0.5f)) {
                Text(
                    text = classification,
                    modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row {
            Button(onClick = { viewModel.loadData() }) {
                Text(text = stringResource(id = R.string.label_btn_reload))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FearGreedIndexWidgetTheme {
        FearGreedScreen()
    }
}
