package barant.curso.simpsonsapi.feature.character.data.local.sharedPreference

import barant.curso.simpsonsapi.core.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterSharedPreferenceDataLocalSourceTest {
    @RelaxedMockK
    private lateinit var sharedPreference: CharacterSharedPreferenceDataLocalSource

    @Before
    fun setUp(){
        MockKAnnotations.init(this,true)
    }

    @Test
    fun `when room getCharacter, return success data`() = runBlocking {
        //Given
        val fakelist: MutableList<barant.curso.simpsonsapi.feature.character.domain.Character> = mutableListOf(
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
        coEvery { sharedPreference.getCharacter() } returns Result.success(fakelist)

        //Then
        val result = sharedPreference.getCharacter()

        //Then
        coVerify(exactly = 1) { sharedPreference.getCharacter() }
        assert(result.isSuccess)
        assert(sharedPreference.getCharacter().getOrNull() == fakelist)
    }

    @Test
    fun `when room getCharacter, return failure data`() = runBlocking {
        //Given
        coEvery { sharedPreference.getCharacter() } returns Result.failure(ErrorApp.DataEmptyErrorApp)

        //Then
        val result = sharedPreference.getCharacter()

        //Then
        coVerify(exactly = 1) { sharedPreference.getCharacter() }
        assert(result.isFailure)
    }

    @Test
    fun `when room getByIdCharacter, return success data`() = runBlocking {
        //Given
        val idCharacter = 1
        val fakeCharacter: Character =
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
        coEvery { sharedPreference.getByIdCharacter(idCharacter) } returns Result.success(fakeCharacter)

        //Then
        val result = sharedPreference.getByIdCharacter(idCharacter)

        //Then
        coVerify(exactly = 1) { sharedPreference.getByIdCharacter(idCharacter) }
        assert(result.isSuccess)
        assert(result.getOrNull() == fakeCharacter)
    }

    @Test
    fun `when room getByIdCharacter, return failure data`() = runBlocking {
        //Given
        val idCharacter = 1
        coEvery { sharedPreference.getByIdCharacter(idCharacter) } returns Result.failure(ErrorApp.ItemNotFoundErrorApp)

        //Then
        val result = sharedPreference.getByIdCharacter(idCharacter)

        //Then
        coVerify(exactly = 1) { sharedPreference.getByIdCharacter(idCharacter) }
        assert(result.isFailure)
    }

}