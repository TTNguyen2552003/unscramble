package app.kotlin.unscramble.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.displayMedium
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.labelMedium
import app.kotlin.unscramble.ui.theme.onBackground
import app.kotlin.unscramble.ui.theme.onPrimary
import app.kotlin.unscramble.ui.theme.primary

@Preview
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        //
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = background.copy(alpha = 0.8f)
                ),
            content = {}
        )

        Column(
            modifier = Modifier
                .padding(top = 160.dp)
                .align(
                    alignment = Alignment.TopCenter
                )
        ) {
            Text(
                text = "Welcome",
                color = onBackground,
                style = displayMedium
            )
            Column(
                modifier = Modifier
                    .padding(top = 72.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                MenuButton(content = "Start", action = {})
                Spacer(modifier = Modifier.height(28.dp))
                MenuButton(content = "Top score", action = {})
                Spacer(modifier = Modifier.height(28.dp))
                MenuButton(content = "Tutorial", action = {})
            }
        }

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(
                    end = 32.dp,
                    bottom = 32.dp
                )
                .align(alignment =Alignment.BottomEnd)
        ) {
            Text(
                text = "Exit",
                color = onBackground,
                style = displaySmall
            )
        }
    }
}

@Composable
fun MenuButton(content: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            .width(132.dp)
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = primary
        ),
        contentPadding = PaddingValues(all = 0.dp)
    ) {
        Text(
            text = content,
            color = onPrimary,
            style = labelMedium
        )
    }
}