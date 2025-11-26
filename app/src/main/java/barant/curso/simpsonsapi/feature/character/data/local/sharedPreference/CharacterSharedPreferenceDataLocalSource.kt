package barant.curso.simpsonsapi.feature.character.data.local.sharedPreference

import barant.curso.simpsonsapi.core.data.remote.local.xml.SharedPreferenceStorage
import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CharacterSharedPreferenceDataLocalSource (private val sharedPreferenceStorage: SharedPreferenceStorage){

    private val prefsKey = "characters"

    suspend fun getAllCharacters(): Result<List<Character>>{
        val listType: Type = object : TypeToken<List<Character>>() {}.type
        val list: List<Character> = sharedPreferenceStorage.get(prefsKey, listType)
            ?: return Result.failure(ErrorApp.CacheEmptyErrorApp)
        return Result.success(list)
    }

    suspend fun saveAll(page: Int, list : List<Character>){
        sharedPreferenceStorage.create(prefsKey, "page: $page, $list")
    }


    suspend fun getByIdCharacter(id: Int): Result<Character> {
        return getAllCharacters().fold(
            onSuccess = { list ->
                val character = list.find { it.id == id }
                if (character != null){
                    Result.success(character)
                } else {
                    Result.failure(ErrorApp.ItemNotFoundErrorApp)
                }
            },
            onFailure = {
                Result.failure(ErrorApp.CacheEmptyErrorApp)
            }
        )
    }
    suspend fun remove(){
        sharedPreferenceStorage.remove(prefsKey)
    }
}