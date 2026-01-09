package barant.curso.simpsonsapi.feature.character.di

import barant.curso.simpsonsapi.feature.character.data.CharacterDataSourceRepository
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiService
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharactersApiRemoteDataSource
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
import barant.curso.simpsonsapi.feature.character.domain.GetByIdCharacterUseCase
import barant.curso.simpsonsapi.feature.character.domain.GetPageCharacterUseCase
import barant.curso.simpsonsapi.feature.character.presentation.detail.CharacterDetailViewModel
import barant.curso.simpsonsapi.feature.character.presentation.list.CharacterListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val characterModule = module {
    // ViewModels
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }

    // Repository
    factoryOf(::CharacterDataSourceRepository) bind CharacterRepository::class

    // Use cases
    factory { GetPageCharacterUseCase(get()) }
    factory { GetByIdCharacterUseCase(get()) }

    // Retrofit service
    single { get<Retrofit>().create(CharacterApiService::class.java) }

    // Dispatcher (mejor single)
    single { Dispatchers.IO }

    // Remote data source
    factory { CharactersApiRemoteDataSource(get(), get()) }

}