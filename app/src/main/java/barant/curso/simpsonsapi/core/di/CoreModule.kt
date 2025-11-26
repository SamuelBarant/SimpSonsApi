package barant.curso.simpsonsapi.core.di

import barant.curso.simpsonsapi.core.di.modules.localModule
import barant.curso.simpsonsapi.core.di.modules.remoteModule
import barant.curso.simpsonsapi.core.di.modules.utilModule
import org.koin.dsl.module

val coreModule = module {
    includes(remoteModule)
    includes(utilModule)
    includes(localModule)
}