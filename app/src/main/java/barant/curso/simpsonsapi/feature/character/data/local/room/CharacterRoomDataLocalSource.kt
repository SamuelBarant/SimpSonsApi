package barant.curso.simpsonsapi.feature.character.data.local.room

import barant.curso.simpsonsapi.core.data.remote.local.cache.mapper.toDomain
import barant.curso.simpsonsapi.core.data.remote.local.cache.mapper.toEntity
import barant.curso.simpsonsapi.core.data.remote.local.cache.repository.CharacterRepository
import barant.curso.simpsonsapi.core.domain.error.ErrorApp
import barant.curso.simpsonsapi.feature.character.domain.Character

class CharacterRoomDataLocalSource (private val repository: CharacterRepository){
    suspend fun getAll(): Result<List<Character>>{
        val result = repository.getAll()
        return if (result.isEmpty()) Result.failure(ErrorApp.CacheEmptyErrorApp)
                else Result.success(result.map { it.toDomain() })
    }

    suspend fun getById(id:Int): Result<Character>{
        val result = repository.getById(id)
        return if (result == null) Result.failure(ErrorApp.ItemNotFoundErrorApp)
                else Result.success(result.toDomain())
    }

    suspend fun insertAll(list: List<Character>): Result<Boolean>{
        repository.insertAll(list.map { it.toEntity() })
        return if (list.isEmpty()) Result.failure(ErrorApp.DataEmptyErrorApp)
                else Result.success(true)
    }

    suspend fun clear(): Result<Boolean>{
        return try {
            repository.clear()
            Result.success(true)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}