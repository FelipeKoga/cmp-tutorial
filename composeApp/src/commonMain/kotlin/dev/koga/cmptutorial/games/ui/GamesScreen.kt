package dev.koga.cmptutorial.games.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import compose.icons.TablerIcons
import compose.icons.tablericons.Calendar
import compose.icons.tablericons.DeviceGamepad
import compose.icons.tablericons.DevicesPc
import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    viewModel: GamesViewModel = koinViewModel(),
    onNavigateToDetails: (id: Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


//    var openBottomSheet by remember { mutableStateOf(false) }
//    if (openBottomSheet) {
//        ModalBottomSheet(
//            onDismissRequest = {},
//
//        ) {
//            Column {
//                ...
//            }
//        }
//    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Games",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                )
            )
        },
    ) { contentPadding ->
        when (val uiState = uiState) {
            Resource.Loading -> Box(Modifier)
            Resource.Error -> Box(Modifier)
            is Resource.Success<List<SimpleGame>> -> {
                LazyColumn(
                    modifier = Modifier.padding(contentPadding),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    items(uiState.data) { game ->
                        GameCard(
                            game = game,
                            onClick = {
                                onNavigateToDetails(game.id)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GameCard(
    game: SimpleGame,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                model = game.thumbnail,
                contentDescription = "thumbnail ${game.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(180.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = game.description,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    maxLines = 3,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    GameInfoBadge(
                        text = game.genre,
                        icon = TablerIcons.DeviceGamepad
                    )
                    GameInfoBadge(
                        text = game.platform,
                        icon = TablerIcons.DevicesPc
                    )
                    GameInfoBadge(
                        text = game.releaseDate.orEmpty(),
                        icon = TablerIcons.Calendar
                    )
                }
            }
        }
    }
}

@Composable
private fun GameInfoBadge(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector
) {
    Badge(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(1.dp))
        Text(
            modifier = Modifier.padding(
                horizontal = 4.dp,
                vertical = 2.dp
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}