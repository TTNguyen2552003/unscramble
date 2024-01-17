package app.kotlin.unscramble

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import app.kotlin.unscramble.navigation.FinalApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
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

            //App content
            FinalApp(context = applicationContext)
        }
    }
}