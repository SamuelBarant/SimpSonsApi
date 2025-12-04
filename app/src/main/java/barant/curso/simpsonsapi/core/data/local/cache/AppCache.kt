package barant.curso.simpsonsapi.core.data.local.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import barant.curso.simpsonsapi.core.presentation.converters.Converters
import barant.curso.simpsonsapi.feature.character.data.local.room.character.CharacterDao
import barant.curso.simpsonsapi.feature.character.data.local.room.character.CharacterEntity
import barant.curso.simpsonsapi.feature.character.data.local.room.remoteKeys.RemoteKeysDao
import barant.curso.simpsonsapi.feature.character.data.local.room.remoteKeys.RemoteKeysEntity

@Database(entities = [CharacterEntity::class, RemoteKeysEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppCache : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}