package com.moony.mvi_sample.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moony.mvi_sample.model.MainIntent
import com.moony.mvi_sample.model.MainSideEffect
import com.moony.mvi_sample.mvi_viewmodel.collectAsState
import com.moony.mvi_sample.mvi_viewmodel.collectSideEffect

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.collectAsState()
    val context = LocalContext.current
    viewModel.collectSideEffect {
        when (it) {
            is MainSideEffect.ShowToast ->
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            20.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                20.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { viewModel.postIntent(MainIntent.Increase) },
                content = {
                    Text("increase")
                }
            )
            Button(
                onClick = { viewModel.postIntent(MainIntent.Decrease) },
                content = {
                    Text("decrease")
                }
            )

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                20.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { viewModel.postIntent(MainIntent.Reset) },
                content = {
                    Text("reset")
                }
            )
            Button(
                onClick = { viewModel.postSideEffect(MainSideEffect.ShowToast("Side Effect")) },
                content = {
                    Text("side effect")
                }
            )
        }
        Text("count: ${uiState.count}")
    }

}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}
