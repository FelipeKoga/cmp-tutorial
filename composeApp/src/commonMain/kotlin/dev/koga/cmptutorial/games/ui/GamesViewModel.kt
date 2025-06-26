package dev.koga.cmptutorial.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.domain.model.Filter
import dev.koga.cmptutorial.games.domain.model.GamePlatform
import dev.koga.cmptutorial.games.domain.model.GamesOrderBy
import dev.koga.cmptutorial.games.domain.repository.GameRepository
import kotlinx.coroutines.flow.*


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

    private val onRetry = SharingRestartable(SharingStarted.WhileSubscribed())

    val uiState = filter.flatMapLatest { filter ->
        repository.getGames(
            platform = filter.platform,
            orderBy = filter.orderBy
        )
    }.stateIn(
        scope = viewModelScope,
        started = onRetry,
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

    fun onRetry() {
        onRetry.restart()
    }
}
