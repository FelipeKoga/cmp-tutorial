package dev.koga.cmptutorial.games.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_tutorial.composeapp.generated.resources.Res
import cmp_tutorial.composeapp.generated.resources.game_screen_error_on_retry_btn
import cmp_tutorial.composeapp.generated.resources.game_screen_error_title
import coil3.compose.AsyncImage
import compose.icons.TablerIcons
import compose.icons.tablericons.Calendar
import compose.icons.tablericons.DeviceGamepad
import compose.icons.tablericons.DevicesPc
import dev.koga.cmptutorial.foundation.model.Resource
import dev.koga.cmptutorial.games.domain.model.SimpleGame
import org.jetbrains.compose.resources.stringResource
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
            Resource.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Resource.Error -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(Res.string.game_screen_error_title),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = {
                                viewModel.onRetry()
                            }
                        ) {
                            Text(text = stringResource(Res.string.game_screen_error_on_retry_btn))
                        }
                    }
                }
            }

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