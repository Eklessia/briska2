package cl.malditosnakamas.briska.companeres.domain

interface CompaneresRepository {
    suspend fun getAll() : List<Companere>
}