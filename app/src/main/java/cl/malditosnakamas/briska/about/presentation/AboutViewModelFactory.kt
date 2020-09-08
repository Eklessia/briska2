package cl.malditosnakamas.briska.about.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.briska.about.domain.ObtainAboutUseCase

class AboutViewModelFactory(
    private val aboutUseCase: ObtainAboutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ObtainAboutUseCase::class.java)
            .newInstance(aboutUseCase)
    }

}