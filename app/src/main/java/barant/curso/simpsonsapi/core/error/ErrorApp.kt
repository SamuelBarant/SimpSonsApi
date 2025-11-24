package barant.curso.simpsonsapi.core.error

sealed class ErrorApp : Throwable() {
    object InternetConnectionErrorApp : ErrorApp()
    object ServerErrorApp : ErrorApp()
    object UnknownErrorApp : ErrorApp()
    object DataEmptyErrorApp : ErrorApp()
    object ItemNotFoundErrorApp : ErrorApp()
}