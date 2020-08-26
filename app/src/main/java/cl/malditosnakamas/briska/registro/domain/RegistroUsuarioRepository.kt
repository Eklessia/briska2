package cl.malditosnakamas.briska.registro.domain

interface RegistroUsuarioRepository {

    suspend fun registro(registroUsuario: RegistroUsuario) : Boolean
}