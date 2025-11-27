package barant.curso.simpsonsapi.core.data.remote.local.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import barant.curso.simpsonsapi.core.data.remote.local.cache.converter.Converters
import barant.curso.simpsonsapi.core.data.remote.local.cache.dao.CharacterDao
import barant.curso.simpsonsapi.core.data.remote.local.cache.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
