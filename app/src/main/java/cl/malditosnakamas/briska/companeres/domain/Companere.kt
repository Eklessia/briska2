package cl.malditosnakamas.briska.companeres.domain

data class Companere(
    val id: Int,
    val name: String,
    val mode: Mode,
    val nivel: Nivel
)

data class Mode(
    val name: String,
    val url: String
)

data class Nivel(
    val id: Int,
    val name: String,
    val descripcion: String,
    val berries: String
)