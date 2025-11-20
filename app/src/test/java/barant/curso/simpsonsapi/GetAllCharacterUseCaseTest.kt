package barant.curso.simpsonsapi

import barant.curso.simpsonsapi.feature.character.domain.Character
import barant.curso.simpsonsapi.feature.character.domain.CharacterRepository
import barant.curso.simpsonsapi.feature.character.domain.GetAllCharacterUseCase
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
        val phrases: List<String> = listOf("Doh!", "Why you little...!", "Woo-hoo!", "Mmm... (food)... *drooling*", "Stupid Flanders!", "Shut up Flanders!", "AAAAGHH!", "Lisa, knock off that racket!", "Uh oh, the boss.", "Lets all go out for frosty chocolate milkshakes!", "Whatever, Ill be at Moes.", "I am evil Ho-mer! I am evil Ho-mer! I am evil Ho-mer!", "Better them than me.", "Better them than me... Oh wait, that was me.", "Marge, my face hurts again!")
        val fakelist: MutableList<Character> = mutableListOf(Character(1, "homer", 25, "Hombre", "15/5/2006", "Nuclear", "alive", phrases, "lkj"))
        coEvery { repo.getAllCharacter() } returns Result.success(fakelist)

        //When
        val result = getAll()

        //Then
        coVerify(exactly = 1){ repo.getAllCharacter() }
        assert(result.isSuccess)
        assert(result.getOrNull() == fakelist)
    }

    @Test
    fun `when App don't return data`() = runBlocking {
        //Given
        coEvery { repo.getAllCharacter() } returns Result.success(emptyList())

        //When
        val result = getAll()

        //Then
        assert(result.isFailure)
        coVerify(exactly = 1){ repo.getAllCharacter() }
    }
}