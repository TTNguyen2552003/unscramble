package app.kotlin.unscramble.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.kotlin.unscramble.R
import app.kotlin.unscramble.data.Player
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.fontScale
import app.kotlin.unscramble.ui.theme.labelLarge
import app.kotlin.unscramble.ui.theme.onBackground
import app.kotlin.unscramble.ui.viewmodels.InputNameScreenViewModel

@Composable
fun InputNameScreen(
    navController: NavController,
    inputNameScreenViewModel: InputNameScreenViewModel = viewModel(
        factory = InputNameScreenViewModel.Factory
    )
) {
    val inputNameScreenUiState: State<Player> = inputNameScreenViewModel.uiState.collectAsState()

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
                value = inputNameScreenUiState.value.playerName,
                onValueChange = { inputNameScreenViewModel.updatePlayerName(newName = it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 44.dp,
                        end = 44.dp,
                    ),
                textStyle = labelLarge.fontScale(),
                trailingIcon =
                {
                    if (inputNameScreenUiState.value.playerName != "")
                        IconButton(
                            onClick = { inputNameScreenViewModel.updatePlayerName(newName = "") }
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
                onClick = {
                    inputNameScreenViewModel.savePlayer()
                    navController.navigate(route = "GameScreen")
                },
                modifier = Modifier
                    .height(64.dp)
                    .width(132.dp),
                shape = RoundedCornerShape(size = 32.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = onBackground
                ),
                enabled = (inputNameScreenUiState.value.playerName != ""),
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