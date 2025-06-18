package dev.koga.cmptutorial.games.di

import dev.koga.cmptutorial.games.data.repository.GameRepositoryImpl
import dev.koga.cmptutorial.games.domain.repository.GameRepository
import dev.koga.cmptutorial.games.ui.GamesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gamesModule = module {
    single<GameRepository> {
        GameRepositoryImpl(
            api = get()
        )
    }

    viewModel {
        GamesViewModel(
            repository = get()
        )
    }
}