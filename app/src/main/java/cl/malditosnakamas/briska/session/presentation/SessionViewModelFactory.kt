package cl.malditosnakamas.briska.session.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.briska.session.domain.ObtenerSessionUseCase

class SessionViewModelFactory(
    private val sessionUseCase: ObtenerSessionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ObtenerSessionUseCase::class.java)
            .newInstance(sessionUseCase)
    }
}