package barant.curso.simpsonsapi.core.di

import barant.curso.simpsonsapi.feature.character.di.characterModule
import org.koin.dsl.module

val featureModule = module {
    includes(characterModule)
}