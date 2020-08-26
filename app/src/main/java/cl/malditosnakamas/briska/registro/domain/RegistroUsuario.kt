package cl.malditosnakamas.briska.registro.domain

data class RegistroUsuario(
    val nombre: String,
    val rut: String,
    val email: String,
    val pass: String
)