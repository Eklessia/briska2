package cl.malditosnakamas.briska.briskeros.domain

interface BriskeroRepository {
    suspend fun getAll() : List<Briskero>
    suspend fun add(briskero: Briskero) : Boolean
}