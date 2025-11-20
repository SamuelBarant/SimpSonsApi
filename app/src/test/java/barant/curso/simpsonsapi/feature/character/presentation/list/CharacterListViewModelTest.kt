package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import barant.curso.simpsonsapi.core.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetAllCharacterUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var useCase: GetAllCharacterUseCase

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp(){
        MockKAnnotations.init(this,true)
        viewModel = CharacterListViewModel(useCase)
    }

    @Test
    fun `when loadCharacter return list`() = runBlocking {
        //Given
        val fakelist: MutableList<Character> = mutableListOf(
            Character(
                1,
                "homer",
                25,
                "Hombre",
                "15/5/2006",
                "Nuclear",
                "alive",
                emptyList(),
                "lkj"
            )
        )
        coEvery { useCase() } returns Result.success(fakelist)

        //When
        viewModel.loadCharacters()

        //Then
        assert(viewModel.uiState.value.data == fakelist)
        assert(!viewModel.uiState.value.isLoading)
        assert(viewModel.uiState.value.error == null)
        coVerify(exactly = 1) { useCase() }
    }

    @Test
    fun `when loadCharacter return error`() = runBlocking {
        //Given
        coEvery { useCase() } returns Result.failure(ErrorApp.ItemNotFoundErrorApp)

        //When
        viewModel.loadCharacters()

        //Then
        assert(viewModel.uiState.value.data == null)
        assert(viewModel.uiState.value.error != null)
        assert(!viewModel.uiState.value.isLoading)
        coVerify(exactly = 1){ useCase() }
    }
}