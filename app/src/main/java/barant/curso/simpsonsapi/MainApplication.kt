package barant.curso.simpsonsapi

import android.app.Application
import barant.curso.simpsonsapi.core.di.characterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(characterModule)
        }
    }

}