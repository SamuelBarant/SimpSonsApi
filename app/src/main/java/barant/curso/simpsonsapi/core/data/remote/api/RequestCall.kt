package barant.curso.simpsonsapi.core.data.remote.api

import android.util.Log
import barant.curso.simpsonsapi.core.error.ErrorApp
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> Result.failure(ErrorApp.InternetConnectionErrorApp)
            is UnknownHostException -> Result.failure(ErrorApp.ServerErrorApp)
            is SocketTimeoutException -> Result.failure(ErrorApp.InternetConnectionErrorApp)
            else -> {
                Log.d("@code",e.toString())
                Result.failure(ErrorApp.UnknownErrorApp)
            }
        }
    }
    return if (response.isSuccessful && response.errorBody() == null) {
        Result.success(response.body()!!)
    } else {
        when (response.code()) {
            else -> {
                Log.d("@code",response.code().toString())
                Result.failure(ErrorApp.UnknownErrorApp)
            }
        }
    }
}
