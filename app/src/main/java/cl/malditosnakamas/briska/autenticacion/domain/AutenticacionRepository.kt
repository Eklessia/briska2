package cl.malditosnakamas.briska.autenticacion.domain

interface AutenticacionRepository {
    suspend fun doLogin(email: String, clave: String) : UserAuth
}
