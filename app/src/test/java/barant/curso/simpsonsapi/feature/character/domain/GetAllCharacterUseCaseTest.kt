package barant.curso.simpsonsapi.feature.character.domain

import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllCharacterUseCaseTest {
    @RelaxedMockK
    private lateinit var repo: CharacterRepository
    lateinit var getAll: GetAllCharacterUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getAll = GetAllCharacterUseCase(repo)
    }

    @Test
    fun `when App return data`() = runBlocking {
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
        coEvery { repo.getAllCharacter() } returns Result.success(fakelist)

        //When
        val result = getAll()

        //Then
        coVerify(exactly = 1) { repo.getAllCharacter() }
        assert(result.isSuccess)
        assert(result.getOrNull() == fakelist)
    }

    @Test
    fun `when App don't return data`() = runBlocking {
        //Given
        coEvery { repo.getAllCharacter() } returns Result.failure(ErrorApp.DataEmptyErrorApp)

        //When
        val result = getAll()
        print(result)

        //Then
        assert(result.isFailure)
        coVerify(exactly = 1) { repo.getAllCharacter() }
    }
}
