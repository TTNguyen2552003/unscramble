package app.kotlin.unscramble.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.bodyMedium
import app.kotlin.unscramble.ui.theme.bodySmall
import app.kotlin.unscramble.ui.theme.displayLarge
import app.kotlin.unscramble.ui.theme.error
import app.kotlin.unscramble.ui.theme.labelLarge
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
import kotlinx.coroutines.delay


@Preview
@Composable
fun GameScreen() {
    var timeoutPreGame: Int by remember {
        mutableIntStateOf(value = 3)
    }
    var timePlay: Int by remember {
        mutableIntStateOf(value = 90)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Content when game start
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                    progress = (timePlay.toFloat() / 90)
                )
                Text(
                    text = timePlay.toString(),
                    style = bodySmall,
                    color = onSurface
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Unscramble",
                style = titleMedium,
                color = onSurface
            )

            Spacer(modifier = Modifier.height(28.dp))

            Card(
                modifier = Modifier
                    .height(160.dp)
                    .width(160.dp),
                shape = RoundedCornerShape(size = 12.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_background),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            QuizCard()

            Spacer(modifier = Modifier.height(28.dp))

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
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(60.dp)
                        .width(132.dp),
                    contentPadding = PaddingValues(all = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = surfaceVariant
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Text(
                        text = "Skip",
                        style = labelLarge,
                        color = onSurface
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(60.dp)
                        .width(132.dp),
                    contentPadding = PaddingValues(all = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primary
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Text(
                        text = "Submit",
                        style = labelLarge,
                        color = onPrimary
                    )
                }
            }
        }

        //Layer with opacity before game starting
        if (timeoutPreGame > 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = background.copy(alpha = 0.9f)
                    )
                    .clickable { },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = timeoutPreGame.toString(),
                    style = displayLarge,
                    color = onBackground
                )
                Button(onClick = { timeoutPreGame-- }) {
                    Text(text = "Click here")
                }
            }
        }

        if (timeoutPreGame > 0)
            LaunchedEffect(key1 = timeoutPreGame) {
                delay(timeMillis = 1000)
                timeoutPreGame--
            }

        if (timeoutPreGame == 0 && timePlay > 0)
            LaunchedEffect(key1 = timePlay) {
                if (timeoutPreGame == 0) {
                    delay(timeMillis = 1000)
                    timePlay--
                }
            }

        //Layer with opacity when game is over
        if (timePlay == 0)
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
                    text = "Over",
                    style = displayLarge,
                    color = onBackground
                )

                Spacer(modifier = Modifier.height(64.dp))

                Text(
                    text = "Score: 80",
                    style = titleMedium,
                    color = onBackground
                )
            }
    }
}

@Composable
fun QuizCard() {
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
                text = "score: 0",
                style = labelMediumRoboto,
                color = onSurface,
            )

            Text(
                text = "turn: 3",
                style = labelMediumRoboto,
                color = onSurface
            )
        }

        Text(
            text = "pplae",
            style = titleMedium,
            color = onSurface,
            modifier = Modifier
                .padding(top = 24.dp)
                .align(alignment = Alignment.TopCenter)
        )

        Text(
            text = "Unscramble the word using all letter",
            style = bodySmall,
            color = onSurface,
            modifier = Modifier
                .padding(top = 80.dp)
                .align(alignment = Alignment.TopCenter)
        )

        var currentAnswer: String by remember {
            mutableStateOf(value = "")
        }
        OutlinedTextField(
            value = currentAnswer,
            onValueChange = { currentAnswer = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .align(alignment = Alignment.BottomCenter),
            trailingIcon = {
                if (currentAnswer != "")
                    IconButton(onClick = { currentAnswer = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Your answer here!",
                    style = bodyMedium,
                    color = onSurface
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = onSurface,
                unfocusedIndicatorColor = onSurface,
                focusedContainerColor = surface,
                unfocusedContainerColor = surface,
                errorIndicatorColor = error
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Text,
            ),
            textStyle = bodyMedium,
            shape = RoundedCornerShape(8.dp)
        )
    }
}