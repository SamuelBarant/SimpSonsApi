package barant.curso.simpsonsapi.core.data.remote.local.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import barant.curso.simpsonsapi.core.data.remote.local.cache.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert
    suspend fun saveAll(list: List<CharacterEntity>)
    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterEntity>
    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getById(id: Int): CharacterEntity?
    @Query("DELETE FROM character")
    suspend fun clear()
}