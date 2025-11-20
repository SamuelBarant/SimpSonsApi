package barant.curso.simpsonsapi

import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
import barant.curso.simpsonsapi.feature.character.domain.GetByIdCharacterUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetByIdCharacterUseCaseTest {
    @RelaxedMockK
    private lateinit var repo: CharacterRepository
    lateinit var getById: GetByIdCharacterUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        getById = GetByIdCharacterUseCase(repo)
    }

    @Test
    fun `when App return data`() = runBlocking {
        //Given
        val characterId = 1
        val phrases = listOf("Doh!", "Woo-hoo!")
        val fakeCharacter = Character(
            id = characterId,
            name = "Homer",
            age = 25,
            gender = "Hombre",
            birthdate = "15/5/2006",
            occupation = "Nuclear",
            status = "alive",
            phrase = phrases,
            img = "lkj"
        )
        coEvery { repo.getByIdCharacter(characterId) } returns Result.success(fakeCharacter)

        //When
        val result = getById(characterId)

        //Then
        coVerify(exactly = 1) { repo.getByIdCharacter(characterId) }
        assert(result.isSuccess)
        assert(fakeCharacter == result.getOrNull())


    }

    @Test
    fun `when App don't return data`() = runBlocking {
        //Given
        val characterId = 1
        val exception = Exception("Character not found")
        coEvery { repo.getByIdCharacter(characterId) } returns Result.failure(exception)

        //When
        val result = getById(characterId)

        //Then
        coVerify(exactly = 1) { repo.getByIdCharacter(characterId) }
        assert(result.isFailure)
        assert(exception == result.exceptionOrNull())


    }
}