package barant.curso.simpsonsapi.feature.character.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import barant.curso.simpsonsapi.core.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetAllCharacterUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var useCase: GetAllCharacterUseCase

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = CharacterListViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadCharacter return list`() = runTest {
        //Given
        val fakelist: List<Character> = listOf(
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
        delay(100)
        val state = viewModel.uiState.value!!
        assert(state.data!!.containsAll(fakelist))
        assert(!state.isLoading)
        assert(state.error == null)
        coVerify(exactly = 1) { useCase() }
    }

    @Test
    fun `when loadCharacter return error`() = runTest {
        //Given
        coEvery { useCase() } returns Result.failure(ErrorApp.ItemNotFoundErrorApp)

        //When
        viewModel.loadCharacters()

        //Then
        delay(100)
        val state = viewModel.uiState.value!!
        assert(state.data == null)
        assert(state.error != null)
        assert(!state.isLoading)
        coVerify(exactly = 1) { useCase() }
    }
}