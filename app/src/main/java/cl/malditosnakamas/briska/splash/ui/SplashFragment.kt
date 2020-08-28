package cl.malditosnakamas.briska.splash.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.malditosnakamas.briska.R
import cl.malditosnakamas.briska.databinding.FragmentSplashBinding
import cl.malditosnakamas.briska.session.data.remote.FirebaseSessionRepository
import cl.malditosnakamas.briska.session.domain.ObtenerSessionUseCase
import cl.malditosnakamas.briska.session.presentation.SessionUiState
import cl.malditosnakamas.briska.session.presentation.SessionViewModel
import cl.malditosnakamas.briska.session.presentation.SessionViewModelFactory
import cl.malditosnakamas.briska.utils.extensions.alert
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var viewModel: SessionViewModel
    private lateinit var viewModelFactory: SessionViewModelFactory
    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentSplashBinding.bind(view)
        setupLiveData()
    }

    private fun setupDependencies() {
        viewModelFactory = SessionViewModelFactory(
            ObtenerSessionUseCase(
                FirebaseSessionRepository(
                    FirebaseAuth.getInstance()
                )
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(SessionViewModel::class.java)
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state -> state?.let { handleState(it) } }
        )
    }

    private fun handleState(state: SessionUiState) {
        when (state) {
            is SessionUiState.Loading -> showLoadingSplash()
            is SessionUiState.SuccessSession -> goToHomeFragment()
            is SessionUiState.InvalidSession -> goToLoginFragment()
        }
    }

    private fun goToLoginFragment() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun goToHomeFragment() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun showLoadingSplash() {
        //TODO Reemplazar este mensaje por una animación Lottie
        alert("Animaciones Lottie?????")
    }
}