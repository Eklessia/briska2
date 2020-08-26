package cl.malditosnakamas.briska.registro.domain

class RegistrarUsuarioUseCase(
    private val registroUsuarioRepository: RegistroUsuarioRepository
) {
    suspend fun excecute(registroUsuario: RegistroUsuario) =
        registroUsuarioRepository.registro(registroUsuario)
}