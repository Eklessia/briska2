package cl.malditosnakamas.briska.about.domain

class ObtainAboutUseCase(
    private val repository: AboutRepository
) {
    suspend fun excecute() = repository.get()
}