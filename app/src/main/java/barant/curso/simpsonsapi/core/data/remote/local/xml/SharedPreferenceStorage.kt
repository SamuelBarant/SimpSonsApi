package barant.curso.simpsonsapi.core.data.remote.local.xml

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import java.lang.reflect.Type

class SharedPreferenceStorage(
    private val context: Context,
    private val gson: Gson
) {
    val prefsKey = "simpsons_cache"
    private val sharedPrefs = context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)

    fun <T> create(key: String, value: T){
        val jsonString = gson.toJson(value)
        sharedPrefs.edit{
            putString(key,jsonString)
        }
    }

    fun <T> get(key: String, typeOfT: Type): T?{
        val jsonString = sharedPrefs.getString(key,null)
        return if (jsonString == null){
            null
        } else{
            gson.fromJson(jsonString,typeOfT)
        }
    }

    fun <T> remove(key: String){
        sharedPrefs.edit {
            remove(key)
        }
    }
}