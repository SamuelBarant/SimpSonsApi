package barant.curso.simpsonsapi.core.data.remote.local.cache.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String?): List<String>? {
        if (json.isNullOrEmpty()) return emptyList()

        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}
