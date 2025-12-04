package barant.curso.simpsonsapi.feature.character.data

import barant.curso.simpsonsapi.feature.character.data.local.sharedPreference.CharacterSharedPreferenceDataLocalSource
import barant.curso.simpsonsapi.feature.character.data.remote.api.CharacterApiDataRemoteSource
import barant.curso.simpsonsapi.feature.character.domain.Character
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterDataSourceRepositoryTest {

    @RelaxedMockK
    lateinit var api: CharacterApiDataRemoteSource

    @RelaxedMockK
    lateinit var sp: CharacterSharedPreferenceDataLocalSource

    @RelaxedMockK
    lateinit var room: CharacterRoomDataLocalSource

    lateinit var repo: CharacterDataSourceRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        repo = CharacterDataSourceRepository(api, sp, room)
    }

    @Test
    fun `when api returns success, repository returns success`() = runBlocking {
        // Given
        val fakelist = listOf(
            Character(
                1, "homer", 25, "Hombre", "15/5/2006",
                "Nuclear", "alive", emptyList(), "lkj"
            )
        )

        coEvery { api.getCharacter() } returns Result.success(fakelist)

        // When
        val result = repo.getAllCharacter()

        // Then
        coVerify(exactly = 1) { api.getCharacter() }
        assert(result.isSuccess)
        assert(result.getOrNull() == fakelist)
    }

    @Test
    fun `when api returns failure, repository returns failure`() = runBlocking {
        // Given
        val error = Exception("network error")
        coEvery { api.getCharacter() } returns Result.failure(error)

        // When
        val result = repo.getAllCharacter()

        // Then
        coVerify(exactly = 1) { api.getCharacter() }
        assert(result.isFailure)
    }

    @Test
    fun `when api getById returns success, repository returns success`() = runBlocking {
        // Given
        val character = Character(
            1, "homer", 25, "Hombre", "15/5/2006",
            "Nuclear", "alive", emptyList(), "lkj"
        )

        coEvery { api.getByIdCharacter(1) } returns Result.success(character)

        // When
        val result = repo.getByIdCharacter(1)

        // Then
        coVerify(exactly = 1) { api.getByIdCharacter(1) }
        assert(result.isSuccess)
        assert(result.getOrNull() == character)
    }

    @Test
    fun `when api getById returns failure, repository returns failure`() = runBlocking {
        // Given
        val error = Exception("id not found")

        coEvery { api.getByIdCharacter(1) } returns Result.failure(error)

        // When
        val result = repo.getByIdCharacter(1)

        // Then
        coVerify(exactly = 1) { api.getByIdCharacter(1) }
        assert(result.isFailure)
    }
}
