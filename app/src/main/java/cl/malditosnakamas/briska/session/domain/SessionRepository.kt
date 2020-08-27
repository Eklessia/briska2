package cl.malditosnakamas.briska.session.domain

interface SessionRepository {
    suspend fun obtainSession() : Session
}
