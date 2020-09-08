package cl.malditosnakamas.briska.autenticacion.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import cl.malditosnakamas.briska.R
import cl.malditosnakamas.briska.autenticacion.data.remote.FirebaseAutenticacionRepository
import cl.malditosnakamas.briska.autenticacion.domain.AutenticacionRepository
import cl.malditosnakamas.briska.autenticacion.domain.LoginUsuarioPassUseCase
import cl.malditosnakamas.briska.databinding.FragmentLoginBinding
import cl.malditosnakamas.briska.databinding.FragmentRegistroUsuarioBinding
import cl.malditosnakamas.briska.utils.extensions.alert
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

   /* private val repository: AutenticacionRepository = FirebaseAutenticacionRepository(
        FirebaseAuth.getInstance()
    )
    private val viewModel by activityViewModels<LoginViewModel> { factory }
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupListener() {

        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.doLogin(
                    etEmailORut.text.toString(),
                    etPass.text.toString()
                )
            }

            btnRegistro.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_registrarUsuarioFragment)
            }
        }
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state -> state?.let { handleState(it) } }
        )
    }

    private fun handleState(state: LoginUiState) {
        when (state) {
            is LoginUiState.Loading -> showLoading()
            is LoginUiState.Success -> showSuccessView()
            is LoginUiState.InvalidUser -> showInvalidUserView()
            is LoginUiState.Error -> showError()
        }
    }

    private fun showError() {
        alert("Error")
    }

    private fun showInvalidUserView() {
        alert("Usuario incorrecto")
    }

    private fun showSuccessView() {
        alert("Login OK")
    }

    private fun showLoading() {
        alert("Cargando")
    }
*/
}