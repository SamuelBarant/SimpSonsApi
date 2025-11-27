package barant.curso.simpsonsapi.core.data.remote.local.cache.repository

import barant.curso.simpsonsapi.core.data.remote.local.cache.dao.CharacterDao
import barant.curso.simpsonsapi.core.data.remote.local.cache.entity.CharacterEntity

class CharacterRepository (private val dao: CharacterDao){
    suspend fun insertAll(list: List<CharacterEntity>) = dao.saveAll(list)
    suspend fun getAll() = dao.getAll()
    suspend fun getById(id:Int) = dao.getById(id)
    suspend fun clear() = dao.clear()
}