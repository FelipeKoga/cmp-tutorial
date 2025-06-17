package dev.koga.cmptutorial.games.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.koga.cmptutorial.foundation.model.Resource


@Composable
fun GamesScreen(
    viewModel: GamesViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is Resource.Success<*> -> {

        }

        Resource.Error -> {
            Text(text = "deu ruim!")
        }
        Resource.Loading -> {
            CircularProgressIndicator()
        }
    }
}