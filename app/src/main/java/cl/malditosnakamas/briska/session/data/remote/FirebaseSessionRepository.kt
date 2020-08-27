package cl.malditosnakamas.briska.session.data.remote

import cl.malditosnakamas.briska.session.domain.Session
import cl.malditosnakamas.briska.session.domain.SessionRepository
import com.google.firebase.auth.FirebaseAuth

class FirebaseSessionRepository(
    private val firebaseAuth: FirebaseAuth
) : SessionRepository {

    override suspend fun obtainSession(): Session {
        val result = firebaseAuth.currentUser
        return Session(result?.displayName?.isNotEmpty() ?: false)
    }

}