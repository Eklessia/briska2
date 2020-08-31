package cl.malditosnakamas.briska.briskeros.domain

class ObtenerBriskerosUseCase(
    private val repository: BriskeroRepository
) {
    suspend fun execute() = repository.getAll()
}