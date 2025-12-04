package barant.curso.simpsonsapi.feature.character.data.local.room.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM character WHERE id is :id")
    suspend fun getById(id: Int): CharacterEntity

    @Query("DELETE FROM character")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM character")
    suspend fun getCount(): Int
}