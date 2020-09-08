package cl.malditosnakamas.briska.about.domain

interface AboutRepository {
    suspend fun get() : About
}