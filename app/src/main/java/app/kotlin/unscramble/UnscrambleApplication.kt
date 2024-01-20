package app.kotlin.unscramble

import android.app.Application
import app.kotlin.unscramble.data.AppContainer
import app.kotlin.unscramble.data.DefaultAppContainer

class UnscrambleApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}