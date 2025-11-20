package barant.curso.simpsonsapi.core.error

sealed class ErrorApp : Throwable() {
    object DataEmptyErrorApp : ErrorApp()
}