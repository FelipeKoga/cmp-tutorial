package dev.koga.cmptutorial.games.ui

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.domain.model.GamePlatform
import dev.koga.cmptutorial.games.domain.model.GamesOrderBy
import dev.koga.cmptutorial.games.domain.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Filter(
    val orderBy: GamesOrderBy?,
    val platform: GamePlatform?,
)

class GamesViewModel(
    private val repository: GameRepository,
) : ViewModel() {

//    private val orderBy = MutableStateFlow<GamesOrderBy?>(null)
//    private val platform = MutableStateFlow<GamePlatform?>(null)
//
//    val uiState = combine(
//        orderBy,
//        platform,
//    ) { orderBy, platform ->
//        repository.getGames(
//            platform = platform,
//            orderBy = orderBy,
//        )
//    }.flatMapLatest { it }


    private val filter = MutableStateFlow(
        Filter(
            orderBy = null,
            platform = null,
        )
    )

    val uiState = filter.flatMapLatest { filter ->
        repository.getGames(
            platform = filter.platform,
            orderBy = filter.orderBy
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )


//    val snackBar = MutableSharedFlow<String>()
//
//    init {
//        viewModelScope.launch {
//            snackBar.emit("Sucesso!")
//        }
//    }

    fun onFilterSubmit(
        orderBy: GamesOrderBy?,
        platform: GamePlatform?,
    ) {
        filter.update {
            it.copy(
                orderBy,
                platform,
            )
        }
    }
}