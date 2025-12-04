package barant.curso.simpsonsapi.feature.character.data.local.room.remoteKeys

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remoteKeys_character WHERE characterId = :id")
    suspend fun remoteKeysCharacterId(id: Int): RemoteKeysEntity?

    @Query("DELETE FROM remoteKeys_character")
    suspend fun clearRemoteKeys()

    @Query("SELECT COUNT(*) FROM remoteKeys_character")
    suspend fun getCount(): Int

    @Query("SELECT MAX(lastUpdated) FROM remoteKeys_character")
    suspend fun getLastFetchTime(): Long?

}