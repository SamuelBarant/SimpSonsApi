    package barant.curso.simpsonsapi.feature.character.di

    import barant.curso.simpsonsapi.feature.character.data.CharacterDataSourceRepository
    import barant.curso.simpsonsapi.feature.character.data.local.room.CharacterRoomDataLocalSource
    import barant.curso.simpsonsapi.feature.character.data.local.sharedPreference.CharacterSharedPreferenceDataLocalSource
    import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiService
    import barant.curso.simpsonsapi.feature.character.data.remote.api.CharactersApiRemoteDataSource
    import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
    import barant.curso.simpsonsapi.feature.character.domain.GetAllCharacterUseCase
    import barant.curso.simpsonsapi.feature.character.domain.GetByIdCharacterUseCase
    import barant.curso.simpsonsapi.feature.character.presentation.details.CharacterDetailViewModel
    import barant.curso.simpsonsapi.feature.character.presentation.details.adapter.CharacterDetailPhrasesAdapter
    import barant.curso.simpsonsapi.feature.character.presentation.list.CharacterListViewModel
    import barant.curso.simpsonsapi.feature.character.presentation.list.adapter.CharacterListItemAdapter
    import kotlinx.coroutines.Dispatchers
    import org.koin.core.module.dsl.factoryOf
    import org.koin.core.module.dsl.singleOf
    import org.koin.core.module.dsl.viewModel
    import org.koin.core.module.dsl.viewModelOf
    import org.koin.dsl.bind
    import org.koin.dsl.module
    import retrofit2.Retrofit

    val characterModule = module {
        // ViewModels
        viewModelOf(::CharacterListViewModel)
        viewModelOf(::CharacterDetailViewModel)

        // UI adapters
        factoryOf(::CharacterListItemAdapter)
        factoryOf(::CharacterDetailPhrasesAdapter)

        // Repository
        factoryOf(::CharacterDataSourceRepository) bind CharacterRepository::class

        // Use cases
        factory { GetAllCharacterUseCase(get()) }
        factory { GetByIdCharacterUseCase(get()) }

        // Retrofit service
        single { get<Retrofit>().create(CharacterApiService::class.java) }

        // Dispatcher (mejor single)
        single { Dispatchers.IO }

        // Remote data source
        factory { CharactersApiRemoteDataSource(get(), get()) }

    }