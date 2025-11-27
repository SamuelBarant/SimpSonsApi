package barant.curso.simpsonsapi.core.di.modules

import androidx.room.Room
import barant.curso.simpsonsapi.core.data.remote.local.cache.database.AppDatabase
import barant.curso.simpsonsapi.core.data.remote.local.cache.repository.CharacterRepository
import barant.curso.simpsonsapi.core.data.remote.local.xml.SharedPreferenceStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin

val localModule = module {
    single { SharedPreferenceStorage(androidContext(), get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "simpsons_cache"
        ).build()
    }
    single { get<AppDatabase>().characterDao() }
    single { CharacterRepository(get()) }
}