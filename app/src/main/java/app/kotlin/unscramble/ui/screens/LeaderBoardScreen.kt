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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.kotlin.unscramble.R
import app.kotlin.unscramble.ui.theme.background
import app.kotlin.unscramble.ui.theme.bodySmall
import app.kotlin.unscramble.ui.theme.displayMedium
import app.kotlin.unscramble.ui.theme.displaySmall
import app.kotlin.unscramble.ui.theme.onBackground

@Preview
@Composable
fun LeaderBoardScreen() {
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
            modifier = Modifier.padding(top = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Top score",
                style = displayMedium,
                color = onBackground
            )

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .height(330.dp)
                    .fillMaxWidth()
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
                        shape = RoundedCornerShape(20.dp)
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 18.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Name",
                        style = displaySmall,
                        color = onBackground
                    )
                    Text(
                        text = "score",
                        style = displaySmall,
                        color = onBackground
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                for (i: Int in 0..9) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                end = 20.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Trump",
                            style = bodySmall,
                            color = onBackground
                        )
                        Text(
                            text = "80",
                            style = bodySmall,
                            color = onBackground
                        )
                    }
                    if (i < 9)
                        Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(
                    end = 28.dp,
                    bottom = 38.dp
                )
                .align(alignment = Alignment.BottomEnd),
        ) {
            Text(
                text = "Back",
                style = displaySmall,
                color = onBackground
            )
        }
    }
}
