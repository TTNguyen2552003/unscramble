package app.kotlin.unscramble.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import app.kotlin.unscramble.ui.theme.bodyMedium
import app.kotlin.unscramble.ui.theme.bodySmall
import app.kotlin.unscramble.ui.theme.labelMediumRoboto
import app.kotlin.unscramble.ui.theme.onSurface
import app.kotlin.unscramble.ui.theme.outlineVariant
import app.kotlin.unscramble.ui.theme.primaryVariant
import app.kotlin.unscramble.ui.theme.surface
import app.kotlin.unscramble.ui.theme.titleMedium
import app.kotlin.unscramble.ui.theme.error

@Preview
@Composable
fun GameScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = surface)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = 36.dp,
                    end = 16.dp
                )
                .align(alignment = Alignment.TopEnd),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Transparent,
                strokeWidth = 3.dp,
                trackColor = primaryVariant,
            )
            Text(
                text = "90",
                style = bodySmall,
                color = onSurface
            )
        }
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
            .background(
                color = outlineVariant,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(size = 12.dp),
                ambientColor = Color.Black.copy(alpha = 0.3f)
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
                IconButton(onClick = { currentAnswer = ""}) {
                    Icons.Default.Clear
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
               errorIndicatorColor = error
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Text,
            )
        )
    }
}