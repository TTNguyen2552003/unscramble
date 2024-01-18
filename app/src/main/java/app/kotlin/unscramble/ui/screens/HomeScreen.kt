package app.kotlin.unscramble.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.kotlin.unscramble.MainActivity
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.displayMedium
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.fontScale
import app.kotlin.unscramble.ui.theme.labelMedium
import app.kotlin.unscramble.ui.theme.onBackground
import kotlin.system.exitProcess

@Composable
fun HomeScreen(navController: NavHostController) {

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
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(bottom = 88.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_message),
                color = onBackground,
                style = displayMedium.fontScale()
            )
            Column(
                modifier = Modifier
                    .padding(top = 52.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(space = 28.dp)
            ) {
                MenuButton(
                    content = stringResource(id = R.string.Start_button_label),
                    action = { navController.navigate(route = "GameScreen") }
                )
                MenuButton(
                    content = stringResource(id = R.string.Top_score_button_label),
                    action = { navController.navigate(route = "LeaderBoardScreen") }
                )
                MenuButton(
                    content = stringResource(id = R.string.Tutorial_button_label),
                    action = { navController.navigate(route = "TutorialScreen") }
                )
            }
        }

        TextButton(
            onClick = {
                val activity = MainActivity()
                activity.finish()
                exitProcess(status = 0)
            },
            modifier = Modifier
                .padding(
                    end = 28.dp,
                    bottom = 38.dp
                )
                .align(alignment = Alignment.BottomEnd)
        ) {
            Text(
                text = stringResource(id = R.string.exit_app),
                color = onBackground,
                style = displaySmall.fontScale()
            )
        }
    }
}

@Composable
fun MenuButton(
    content: String,
    action: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(64.dp)
            .width(132.dp),
        onClick = action,
        colors = ButtonDefaults.buttonColors(containerColor = background.copy(alpha = 0.8f)),
        border = BorderStroke(
            color = onBackground,
            width = 3.dp
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Text(
            text = content,
            style = labelMedium.fontScale(),
            color = onBackground
        )
    }
}