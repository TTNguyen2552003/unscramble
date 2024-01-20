package app.kotlin.unscramble.ui.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.bodySmall
import app.kotlin.unscramble.ui.theme.displayLarge
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.error
import app.kotlin.unscramble.ui.theme.fontScale
import app.kotlin.unscramble.ui.theme.labelLarge
import app.kotlin.unscramble.ui.theme.labelMedium
import app.kotlin.unscramble.ui.theme.labelMediumRoboto
import app.kotlin.unscramble.ui.theme.onBackground
import app.kotlin.unscramble.ui.theme.onPrimary
import app.kotlin.unscramble.ui.theme.onSurface
import app.kotlin.unscramble.ui.theme.outlineVariant
import app.kotlin.unscramble.ui.theme.primary
import app.kotlin.unscramble.ui.theme.primaryVariant
import app.kotlin.unscramble.ui.theme.surface
import app.kotlin.unscramble.ui.theme.surfaceVariant
import app.kotlin.unscramble.ui.theme.titleMedium
import app.kotlin.unscramble.ui.viewmodels.GameScreenViewModel
import app.kotlin.unscramble.ui.viewmodels.GameUiState
import app.kotlin.unscramble.ui.viewmodels.InternetState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlin.random.Random

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun GameScreen(
    gameScreenViewModel: GameScreenViewModel = viewModel(factory = GameScreenViewModel.Factory),
    navController: NavHostController
) {
    val gameUiState: State<GameUiState> = gameScreenViewModel.uiState.collectAsState()
    when (gameUiState.value.internetState) {
        InternetState.LOADING -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.app_background),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )

                //Add a layer with 0.8 opacity
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = background.copy(alpha = 0.8f))
                )

                Column(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.loading_notification),
                        style = displaySmall.fontScale(),
                        color = onBackground
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    //Progress indicator
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(248.dp)
                            .background(
                                color = background.copy(alpha = 0.8f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = onBackground,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(Random.nextInt(from = 0, until = 249).dp)
                                .background(
                                    color = onBackground,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    }
                }
            }
        }

        InternetState.FAILED -> {
            Box(modifier = Modifier.fillMaxSize()) {
                //App background
                Image(
                    painter = painterResource(id = R.drawable.app_background),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )

                //Add a layer with 0.8 opacity
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = background.copy(alpha = 0.8f))
                )

                Column(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.connection_corrupted_notification),
                        style = displaySmall.fontScale(),
                        color = onBackground
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    //Retry button when internet connection corrupted
                    Button(
                        modifier = Modifier
                            .height(64.dp)
                            .width(132.dp),
                        onClick = { gameScreenViewModel.initGame() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = background.copy(alpha = 0.8f)
                        ),
                        border = BorderStroke(
                            color = onBackground,
                            width = 3.dp
                        ),
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.retry_to_connect_button_label),
                            style = labelMedium.fontScale(),
                            color = onBackground
                        )
                    }
                }

                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(
                            bottom = 28.dp,
                            end = 28.dp
                        )
                        .align(alignment = Alignment.BottomEnd)
                ) {
                    Text(
                        text = stringResource(id = R.string.back_button_label),
                        style = displaySmall.fontScale(),
                        color = onBackground
                    )
                }
            }
        }

        InternetState.SUCCESSFUL -> {
            Box(modifier = Modifier.fillMaxSize()) {
                //Content when game starting
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = surface),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Timer
                    Box(
                        modifier = Modifier
                            .padding(
                                top = 36.dp,
                                end = 16.dp
                            )
                            .align(alignment = Alignment.End),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = primaryVariant,
                            strokeWidth = 3.dp,
                            trackColor = Color.Transparent,
                            progress = (gameUiState.value.timePlay.toFloat() / 90)
                        )
                        Text(
                            text = gameUiState.value.timePlay.toString(),
                            style = bodySmall.fontScale(),
                            color = onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //Game title
                    Text(
                        text = stringResource(R.string.app_name),
                        style = titleMedium.fontScale(),
                        color = onSurface
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    //Illustration
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(gameUiState.value.currentWord.url)
                            .crossfade(enable = true)
                            .build(),
                        modifier = Modifier
                            .height(160.dp)
                            .width(160.dp)
                            .clip(shape = RoundedCornerShape(12.dp)),
                        placeholder = painterResource(id = R.drawable.image_place_holder),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    //Quiz card
                    QuizCard(
                        gameUiState = gameUiState,
                        gameViewModel = gameScreenViewModel
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    //The row contains some buttons of action
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 24.dp,
                                end = 24.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Skip button
                        Button(
                            onClick = { gameScreenViewModel.skip() },
                            modifier = Modifier
                                .height(60.dp)
                                .width(132.dp),
                            contentPadding = PaddingValues(all = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = surfaceVariant,
                                disabledContainerColor = surfaceVariant
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 2.dp,
                                disabledElevation = 2.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 4.dp
                            ),
                            enabled = !gameUiState.value.isOver
                                    && gameUiState.value.timeoutPreGame == 0
                        ) {
                            Text(
                                text = stringResource(R.string.skip_button_label),
                                style = labelLarge.fontScale(),
                                color = onSurface
                            )
                        }

                        //Submit button
                        Button(
                            onClick = { gameScreenViewModel.submit() },
                            modifier = Modifier
                                .height(60.dp)
                                .width(132.dp),
                            contentPadding = PaddingValues(all = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primary,
                                disabledContainerColor = primary
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 2.dp,
                                disabledElevation = 2.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 4.dp
                            ),
                            enabled = !gameUiState.value.isOver
                                    && gameUiState.value.timeoutPreGame == 0
                        ) {
                            Text(
                                text = stringResource(R.string.submit_button_label),
                                style = labelLarge.fontScale(),
                                color = onPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(height = 28.dp))

                    //Notification when user submit the answer
                    if (gameUiState.value.notificationShown) {
                        Text(
                            text = gameUiState.value.notification,
                            style = titleMedium.fontScale(),
                            color = primaryVariant
                        )
                    }
                }

                //Layer to maker player input his/her name
                var isUserNameInput: Boolean by remember { mutableStateOf(value = false) }
                if (!isUserNameInput) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.app_background),
                            contentDescription = "",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxSize()
                        )

                        //Add a layer with 0.8 opacity
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = background.copy(alpha = 0.8f))
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(alignment = Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(28.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.input_name_display),
                                style = labelLarge.fontScale(),
                                color = onBackground
                            )

                            OutlinedTextField(
                                value = gameUiState.value.userName,
                                onValueChange = { gameScreenViewModel.updateUserName(newName = it) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 44.dp,
                                        end = 44.dp,
                                    ),
                                textStyle = labelLarge.fontScale(),
                                trailingIcon =
                                {
                                    if (gameUiState.value.userName != "")
                                        IconButton(
                                            onClick = {
                                                gameScreenViewModel.updateUserName(newName = "")
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "",
                                                tint = onBackground
                                            )
                                        }
                                },
                                singleLine = true,
                                maxLines = 1,
                                shape = RoundedCornerShape(size = 8.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = onBackground,
                                    unfocusedTextColor = onBackground,
                                    focusedContainerColor = background.copy(alpha = 0.8f),
                                    unfocusedContainerColor = background.copy(alpha = 0.8f),
                                    focusedBorderColor = onBackground,
                                    unfocusedBorderColor = onBackground,
                                    cursorColor = onBackground
                                )
                            )

                            Button(
                                onClick = { isUserNameInput = true },
                                modifier = Modifier
                                    .height(64.dp)
                                    .width(132.dp),
                                shape = RoundedCornerShape(size = 32.dp),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = onBackground
                                ),
                                enabled = (gameUiState.value.userName != ""),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = background.copy(alpha = 0.8f),
                                    disabledContainerColor = background.copy(alpha = 0.8f),
                                    disabledContentColor = background,
                                    contentColor = onBackground
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.play_button_label),
                                    style = labelLarge.fontScale()
                                )
                            }
                        }

                        //Back button if player don't want to play anymore
                        TextButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(
                                    bottom = 28.dp,
                                    end = 28.dp
                                )
                                .align(alignment = Alignment.BottomEnd)
                        ) {
                            Text(
                                text = stringResource(id = R.string.back_button_label),
                                style = displaySmall.fontScale(),
                                color = onBackground
                            )
                        }
                    }
                }

                //Layer with opacity before game starting
                if (gameUiState.value.timeoutPreGame > 0 && isUserNameInput) {
                    LaunchedEffect(key1 = gameUiState.value.timeoutPreGame) {
                        delay(timeMillis = 1000)
                        gameScreenViewModel.decreaseTimeoutPreGame()
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = background.copy(alpha = 0.9f)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = gameUiState.value.timeoutPreGame.toString(),
                            style = displayLarge.fontScale(),
                            color = onBackground
                        )
                    }
                }

                //Layer with opacity when game is over
                if (
                    gameUiState.value.timeoutPreGame == 0
                    && !gameUiState.value.isOver
                )
                    LaunchedEffect(key1 = gameUiState.value.timePlay) {
                        delay(timeMillis = 1000)
                        gameScreenViewModel.decreaseTimePlay()
                    }

                if (gameUiState.value.isOver)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = background.copy(alpha = 0.9f))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.game_over_notification),
                                style = displayLarge.fontScale(),
                                color = onBackground
                            )

                            Spacer(modifier = Modifier.height(64.dp))

                            Text(
                                text = stringResource(R.string.score) + "${gameUiState.value.score}",
                                style = titleMedium.fontScale(),
                                color = onBackground
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 28.dp,
                                    end = 28.dp,
                                    bottom = 38.dp
                                )
                                .align(alignment = Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //Main Menu button
                            TextButton(
                                onClick = {
                                    navController.navigate(route = "HomeScreen") { popUpTo(id = 0) }
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.back_to_main_menu_button_label),
                                    style = labelLarge.fontScale(),
                                    color = onBackground
                                )
                            }

                            //Try again button
                            TextButton(onClick = { gameScreenViewModel.resetGame() }) {
                                Text(
                                    text = stringResource(R.string.try_again_button_label),
                                    style = labelLarge.fontScale(),
                                    color = onBackground
                                )
                            }
                        }
                    }
            }
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun QuizCard(
    gameUiState: State<GameUiState>,
    gameViewModel: GameScreenViewModel
) {
    Box(
        modifier = Modifier
            .height(210.dp)
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp
            )
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = outlineVariant,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.score) + "${gameUiState.value.score}",
                style = labelMediumRoboto.fontScale(),
                color = onSurface,
            )

            Text(
                text = stringResource(R.string.turn) + "${gameUiState.value.turn}",
                style = labelMediumRoboto.fontScale(),
                color = onSurface
            )
        }

        Text(
            text = gameUiState.value.currentQuiz,
            style = titleMedium.fontScale(),
            color = onSurface,
            modifier = Modifier
                .padding(top = 24.dp)
                .align(alignment = Alignment.TopCenter)
        )

        Text(
            text = stringResource(R.string.basic_game_rule),
            style = bodySmall.fontScale(),
            color = onSurface,
            modifier = Modifier
                .padding(top = 80.dp)
                .align(alignment = Alignment.TopCenter)
        )

        OutlinedTextField(
            value = gameUiState.value.currentAnswer,
            onValueChange = { gameViewModel.updateCurrentAnswer(newAnswer = it) },
            readOnly = gameUiState.value.isOver || gameUiState.value.timeoutPreGame > 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .align(alignment = Alignment.BottomCenter),
            trailingIcon = {
                if (gameUiState.value.currentAnswer != "")
                    IconButton(onClick = { gameViewModel.updateCurrentAnswer(newAnswer = "") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.answer_place_holder),
                    style = labelLarge.fontScale(),
                    color = onSurface
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = onSurface,
                unfocusedIndicatorColor = onSurface,
                focusedContainerColor = surface,
                unfocusedContainerColor = surface,
                errorIndicatorColor = error,
                cursorColor = onSurface
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Text,
            ),
            textStyle = labelLarge.fontScale(),
            shape = RoundedCornerShape(8.dp)
        )
    }
}