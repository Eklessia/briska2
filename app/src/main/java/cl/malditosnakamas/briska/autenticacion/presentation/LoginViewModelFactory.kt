package cl.malditosnakamas.briska.autenticacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.briska.autenticacion.domain.LoginUsuarioPassUseCase

class LoginViewModelFactory(
    private val loginUsuarioPassUseCase: LoginUsuarioPassUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginUsuarioPassUseCase::class.java)
            .newInstance(loginUsuarioPassUseCase)
    }
}