package cl.malditosnakamas.briska.autenticacion.data.remote

import cl.malditosnakamas.briska.autenticacion.domain.AutenticacionRepository
import cl.malditosnakamas.briska.autenticacion.domain.UserAuth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAutenticacionRepository(
    private val firebaseAuth: FirebaseAuth
) : AutenticacionRepository {

    override suspend fun doLogin(email: String, clave: String): UserAuth {
        firebaseAuth
            .signInWithEmailAndPassword(email, clave)
            .await()
        val user = firebaseAuth.currentUser
        return UserAuth(user?.displayName ?: "", user?.email ?: "")
    }
}