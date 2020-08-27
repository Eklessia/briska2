package cl.malditosnakamas.briska.session.domain

class ObtenerSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend fun excecute() = sessionRepository.obtainSession()
}