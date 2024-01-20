package app.kotlin.unscramble

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import app.kotlin.unscramble.services.AudioService

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.White.toArgb(),
                darkScrim = Color.Black.toArgb()
            )
        )
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            //Lock orientation
            val activity: Activity? = (LocalContext.current as? Activity)
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            //Set up immersive mode
            val controller: WindowInsetsController? = window.insetsController
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller?.hide(WindowInsets.Type.navigationBars())

            val serviceIntent = Intent(this,AudioService::class.java)
            startForegroundService(serviceIntent)
            //App content
            FinalApp()
        }
    }
}