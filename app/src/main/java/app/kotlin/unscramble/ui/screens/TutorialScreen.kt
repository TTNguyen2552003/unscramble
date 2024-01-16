package app.kotlin.unscramble.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.bodySmall
import app.kotlin.unscramble.ui.theme.displayMedium
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.labelMediumRoboto
import app.kotlin.unscramble.ui.theme.onBackground


@Composable
fun TutorialScreen(
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        //Add background image
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
                .background(
                    color = background.copy(alpha = 0.8f)
                )
        )

        Column(
            modifier = Modifier.padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(space = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tutorial",
                style = displayMedium,
                color = onBackground
            )

            GameRule()

            HowToPlay()
        }

        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(
                    end = 28.dp,
                    bottom = 38.dp
                )
                .align(alignment = Alignment.BottomEnd)
        ) {
            Text(
                text = "Back",
                style = displaySmall,
                color = onBackground
            )
        }
    }
}

@Composable
fun GameRule() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(272.dp)
            .padding(
                start = 28.dp,
                end = 28.dp
            )
            .background(
                color = background.copy(alpha = 0.8f),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .border(
                width = 4.dp,
                color = onBackground,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 44.dp
        )
    ) {
        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Text(
                text = "Game rules",
                style = displaySmall,
                color = onBackground
            )
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Section(
                numbered = 1,
                title = "Time Limit",
                content = "You have a total of 90 seconds to play the game." +
                        " The countdown begins as soon as you start."
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 2,
                title = "Scoring",
                content = "For each correct answer you submit, " +
                        "you earn points equal to the length of the unscrambled word.",
                content2 = "Example: If the word is \"KOTLIN,\"" +
                        " and you submit the correct answer, you earn 6 points.",
                content3 = "You can give bonus points if you have winning streak."
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 3,
                title = "Turns",
                content = "You have 3 turns to answer each quiz.",
                content2 = "If you provide a correct answer within the allowed turns," +
                        " you earn points, and a new quiz is presented.",
                content3 = "If you run out of turns without a correct answer," +
                        " a new quiz is presented."
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 4,
                title = "Skip Option",
                content = "For each correct answer you submit, " +
                        "you earn points equal to the length of the unscrambled word.",
            )
        }

        item {
            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .background(color = Color.Transparent)
            )
        }
    }
}

@Composable
fun HowToPlay() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(272.dp)
            .padding(
                start = 28.dp,
                end = 28.dp
            )
            .background(
                color = background.copy(alpha = 0.8f),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .border(
                width = 4.dp,
                color = onBackground,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 44.dp
        )
    ) {
        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Text(
                text = "How to play",
                style = displaySmall,
                color = onBackground
            )
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Section(
                numbered = 1,
                title = "Unscramble the Word",
                content = "Look at the scrambled word presented on the screen.",
                content2 = "Rearrange the letters to form the correct word."
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 2,
                title = "Submit Your Answer",
                content = "Type your answer in the provided text box.",
                content2 = "Click the \"Submit\" button to submit your answer."
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 3,
                title = "Score Points",
                content = "If your answer is correct, you earn points.",
                content2 = "The points contribute to your overall score.",
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 4,
                title = "Use Turns Wisely",
                content = "You have a limited number of turns to answer each quiz.",
                content2 = "Be strategic and consider using the \"Skip\" option if needed."
            )
        }

        item {
            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .background(color = Color.Transparent)
            )
        }
    }
}

@Composable
fun Section(
    numbered: Int,
    title: String,
    content: String,
    content2: String = "",
    content3: String = ""
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //Title
        Text(
            text = "${numbered}. $title",
            style = labelMediumRoboto,
            color = onBackground
        )

        //Content
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "\u2022",
                style = bodySmall,
                color = onBackground
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = content,
                style = bodySmall,
                color = onBackground
            )
        }

        if (content2 != "") {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "\u2022",
                    style = bodySmall,
                    color = onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = content2,
                    style = bodySmall,
                    color = onBackground
                )
            }
        }

        if (content3 != "") {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "\u2022",
                    style = bodySmall,
                    color = onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = content3,
                    style = bodySmall,
                    color = onBackground
                )
            }
        }
    }
}