package cl.malditosnakamas.briska.autenticacion.presentation

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import cl.malditosnakamas.briska.autenticacion.domain.LoginUsuarioPassUseCase

class LoginViewModelFactory(
    private val loginUsuarioPassUseCase: LoginUsuarioPassUseCase,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return LoginViewModel(loginUsuarioPassUseCase, handle) as T
    }
}