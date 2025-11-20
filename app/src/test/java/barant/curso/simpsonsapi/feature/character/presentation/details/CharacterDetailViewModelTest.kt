package barant.curso.simpsonsapi.feature.character.presentation.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import barant.curso.simpsonsapi.core.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.GetByIdCharacterUseCase
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

class CharacterDetailViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var useCase: GetByIdCharacterUseCase

    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = CharacterDetailViewModel(useCase)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel returns success`() = runTest {
        //Given
        val idCharacter = 1
        val fakeCharacter =
            Character(
                idCharacter,
                "homer",
                25,
                "Hombre",
                "15/5/2006",
                "Nuclear",
                "alive",
                emptyList(),
                "lkj"
            )
        coEvery { useCase(idCharacter) } returns Result.success(fakeCharacter)

        //When
        viewModel.loadCharacter(idCharacter)

        //Then
        delay(100)
        val status = viewModel.uiState.value!!
        assert(status.data == fakeCharacter)
        assert(!status.isLoading)
        assert(status.error == null)
        coVerify(exactly = 1) { useCase(idCharacter) }
    }

    @Test
    fun `when viewModel returns failure`() = runTest {
        //Given
        val idCharacter = 1
        coEvery { useCase(idCharacter) } returns Result.failure(ErrorApp.ItemNotFoundErrorApp)

        //When
        viewModel.loadCharacter(idCharacter)

        //Then
        delay(100)
        val result = viewModel.uiState.value!!
        assert(result.data == null)
        assert(!result.isLoading)
        assert(result.error != null)
        coVerify(exactly = 1){useCase(idCharacter)}
    }
}