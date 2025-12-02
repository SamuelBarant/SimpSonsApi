package barant.curso.simpsonsapi.feature.character.di

import barant.curso.simpsonsapi.feature.character.data.CharacterDataSourceRepository
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiService
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharactersApiRemoteDataSource
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
import barant.curso.simpsonsapi.feature.character.domain.GetPageCharacterUseCase
import barant.curso.simpsonsapi.feature.character.presentation.list.CharacterListViewModel
import barant.curso.simpsonsapi.feature.character.presentation.list.adapter.CharacterListItemAdapter
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val characterModule = module {
    // ViewModels
    viewModel { CharacterListViewModel(get()) }

    // UI adapters
    factoryOf(::CharacterListItemAdapter)

    // Repository
    factoryOf(::CharacterDataSourceRepository) bind CharacterRepository::class

    // Use cases
    factory { GetPageCharacterUseCase(get()) }

    // Retrofit service
    single { get<Retrofit>().create(CharacterApiService::class.java) }

    // Dispatcher (mejor single)
    single { Dispatchers.IO }

    // Remote data source
    factory { CharactersApiRemoteDataSource(get(), get()) }

}