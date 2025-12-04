package barant.curso.simpsonsapi.core.di.modules

import androidx.room.Room
import barant.curso.simpsonsapi.core.data.local.cache.AppCache
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single<AppCache> {
        Room.databaseBuilder(
            androidApplication(),
            AppCache::class.java,
            "app_cache"
        ).build()
    }

    single { get<AppCache>().characterDao() }
    single { get<AppCache>().remoteKeysDao() }
}