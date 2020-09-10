package cl.malditosnakamas.briska.companeres.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.briska.companeres.domain.ObtainCompaneresUseCase

class CompaneresViewModelFactory(
    private val obtainCompaneresUseCase: ObtainCompaneresUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ObtainCompaneresUseCase::class.java)
            .newInstance(obtainCompaneresUseCase)
    }

}