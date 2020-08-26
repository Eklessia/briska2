package cl.malditosnakamas.briska.registro.data.remote

import cl.malditosnakamas.briska.registro.domain.RegistroUsuario
import cl.malditosnakamas.briska.registro.domain.RegistroUsuarioRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class FirebaseRegistroUsuarioRepository(
    private val firebaseAuth: FirebaseAuth
) : RegistroUsuarioRepository {
    override suspend fun registro(registroUsuario: RegistroUsuario): Boolean {
        val result = firebaseAuth
            .createUserWithEmailAndPassword(registroUsuario.email, registroUsuario.pass)
            .await()
        //TODO refactorizar este update
        val user = firebaseAuth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(registroUsuario.nombre)
            .build()
        user?.updateProfile(profileUpdates)?.await()
        return result.user?.email?.isNotEmpty() ?: false
    }
}