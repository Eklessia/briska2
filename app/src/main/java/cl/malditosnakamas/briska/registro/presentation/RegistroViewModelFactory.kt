package cl.malditosnakamas.briska.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.briska.registro.domain.RegistrarUsuarioUseCase

class RegistroViewModelFactory(
    private val registrarUsuarioUseCase: RegistrarUsuarioUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegistrarUsuarioUseCase::class.java)
            .newInstance(registrarUsuarioUseCase)
    }
}