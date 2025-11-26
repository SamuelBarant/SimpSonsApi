package barant.curso.simpsonsapi.core.di.modules

import com.google.gson.Gson
import org.koin.dsl.module

val utilModule = module {
    single { Gson() }
}