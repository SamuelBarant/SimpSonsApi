package barant.curso.simpsonsapi.core.di.modules

import barant.curso.simpsonsapi.core.data.remote.local.xml.SharedPreferenceStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { SharedPreferenceStorage(androidContext(), get()) }
}