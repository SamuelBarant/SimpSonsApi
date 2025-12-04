package barant.curso.simpsonsapi.feature.character.data.local.room.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeys_character")
data class RemoteKeysEntity(
    @PrimaryKey val characterId: Int,
    val nextKey: Int?,
    val prevKey: Int?,
    val lastUpdated: Long = System.currentTimeMillis()
)