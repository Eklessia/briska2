package cl.malditosnakamas.briska.briskeros.domain

class RegistrarBriskeroUseCase(
    private val repository: BriskeroRepository
) {
    suspend fun execute(briskero: Briskero) = repository.add(briskero)
}