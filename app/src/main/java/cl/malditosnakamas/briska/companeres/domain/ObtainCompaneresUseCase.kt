package cl.malditosnakamas.briska.companeres.domain

class ObtainCompaneresUseCase(
    private val repository: CompaneresRepository
) {
    suspend fun execute() = repository.getAll()
}