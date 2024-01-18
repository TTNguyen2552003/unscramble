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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.bodySmall
import app.kotlin.unscramble.ui.theme.displayMedium
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.fontScale
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
                .background(color = background.copy(alpha = 0.8f))
        )

        Column(
            modifier = Modifier.padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(space = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.Tutorial),
                style = displayMedium.fontScale(),
                color = onBackground,
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
                text = stringResource(id = R.string.back_button_label),
                style = displaySmall.fontScale(),
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
                text = stringResource(id = R.string.Game_rules_h1),
                style = displaySmall.fontScale(),
                color = onBackground
            )
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Section(
                numbered = 1,
                title = stringResource(id = R.string.Time_limit_h2),
                content = stringResource(id = R.string.Time_limit_body)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 2,
                title = stringResource(id = R.string.Scoring_h2),
                content = stringResource(id = R.string.Scoring_body_1),
                content2 = stringResource(id = R.string.Scoring_body_2),
                content3 = stringResource(id = R.string.Scoring_body_3)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 3,
                title = stringResource(id = R.string.Turns_h2),
                content = stringResource(id = R.string.Turns_body_1),
                content2 = stringResource(id = R.string.Turns_body_2),
                content3 = stringResource(id = R.string.Turns_body_3)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 4,
                title = stringResource(id = R.string.Skip_Option_h2),
                content = stringResource(id = R.string.Skip_Option_body)
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
                text = stringResource(id = R.string.How_to_play_h1),
                style = displaySmall.fontScale(),
                color = onBackground
            )
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            Section(
                numbered = 1,
                title = stringResource(id = R.string.Unscramble_the_Word_h2),
                content = stringResource(id = R.string.Unscramble_the_Word_body_1),
                content2 = stringResource(id = R.string.Unscramble_the_Word_body_2)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 2,
                title = stringResource(id = R.string.Submit_Your_Answer_h2),
                content = stringResource(id = R.string.Submit_Your_Answer_body_1),
                content2 = stringResource(id = R.string.Submit_Your_Answer_body_2)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 3,
                title = stringResource(id = R.string.Score_Points_h2),
                content = stringResource(id = R.string.Score_Points_body_1),
                content2 = stringResource(id = R.string.Score_Points_body_2),
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Section(
                numbered = 4,
                title = stringResource(id = R.string.Use_Turns_Wisely_h2),
                content = stringResource(id = R.string.Use_Turns_Wisely_body_1),
                content2 = stringResource(id = R.string.Use_Turns_Wisely_body_1)
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
            style = labelMediumRoboto.fontScale(),
            color = onBackground
        )

        //Content
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "\u2022",
                style = bodySmall.fontScale(),
                color = onBackground
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = content,
                style = bodySmall.fontScale(),
                color = onBackground
            )
        }

        if (content2 != "") {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "\u2022",
                    style = bodySmall.fontScale(),
                    color = onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = content2,
                    style = bodySmall.fontScale(),
                    color = onBackground
                )
            }
        }

        if (content3 != "") {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "\u2022",
                    style = bodySmall.fontScale(),
                    color = onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = content3,
                    style = bodySmall.fontScale(),
                    color = onBackground
                )
            }
        }
    }
}