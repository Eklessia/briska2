package cl.malditosnakamas.briska.registro.data.remote

import cl.malditosnakamas.briska.registro.domain.RegistroUsuario
import cl.malditosnakamas.briska.registro.domain.RegistroUsuarioRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRegistroUsuarioRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDataBase: FirebaseDatabase
) : RegistroUsuarioRepository {
    override suspend fun registro(registroUsuario: RegistroUsuario): Boolean {
        val result = creaUsuarioEnFirebase(registroUsuario.email, registroUsuario.pass)
        agregarNombreAFirebase(registroUsuario.nombre)
        agregarDatosPersonalesAFirebase(
            registroUsuario.nombre,
            registroUsuario.rut,
            registroUsuario.email
        )
        return result.user?.email?.isNotEmpty() ?: false
    }

    private suspend fun creaUsuarioEnFirebase(email: String, pass: String): AuthResult {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, pass)
            .await()
    }

    private suspend fun agregarNombreAFirebase(
        nombre: String
    ) {
        val user = firebaseAuth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(nombre)
            .build()
        user?.updateProfile(profileUpdates)?.await()
    }

    private suspend fun agregarDatosPersonalesAFirebase(
        nombre: String,
        rut: String,
        email: String
    ) {
        val dataBase = firebaseDataBase.getReference("usuarios/${rut}")
        val registroUsuarioFirebase = RegistroUsuarioFirebase(
            nombre,
            rut,
            email
        )
        dataBase.setValue(registroUsuarioFirebase).await()
    }
}