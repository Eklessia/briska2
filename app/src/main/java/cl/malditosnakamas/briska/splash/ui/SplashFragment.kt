package cl.malditosnakamas.briska.splash.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cl.malditosnakamas.briska.R
import cl.malditosnakamas.briska.databinding.FragmentSplashBinding
import cl.malditosnakamas.briska.session.domain.ObtenerSessionUseCase
import cl.malditosnakamas.briska.session.domain.SessionRepository
import cl.malditosnakamas.briska.session.presentation.SessionViewModel
import cl.malditosnakamas.briska.session.presentation.SessionViewModelFactory

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var useCase: ObtenerSessionUseCase
    private lateinit var viewModel: SessionViewModel
    private lateinit var viewModelFactory: SessionViewModelFactory
    private lateinit var repository: SessionRepository
    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

    }

}