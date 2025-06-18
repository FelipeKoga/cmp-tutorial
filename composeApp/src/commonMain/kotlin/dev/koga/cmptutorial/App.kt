package dev.koga.cmptutorial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cmp_tutorial.composeapp.generated.resources.Res
import cmp_tutorial.composeapp.generated.resources.compose_multiplatform
import dev.koga.cmptutorial.foundation.designsystem.MyTheme
import dev.koga.cmptutorial.games.ui.GamesScreen
import kotlinx.serialization.Serializable

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    MyTheme {
        NavHost(
            navController = navController,
            startDestination = Routes.Games,
        ) {

            composable<Routes.Games> {
                GamesScreen(
                    onNavigateToDetails = { id ->
                        navController.navigate(Routes.GameDetails(id))
                    }
                )
            }

            composable<Routes.GameDetails> { backStack ->
                val id = backStack.toRoute<Routes.GameDetails>().id

                Column() {
                    Text(text = "Detalhes $id")
                }
            }

//            dialog<> {
//                ModalBottomSheet(
//                    v
//                ) {
//
//                }
//            }
        }
    }
}
