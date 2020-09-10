package cl.malditosnakamas.briska.autenticacion.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.malditosnakamas.briska.R
import cl.malditosnakamas.briska.autenticacion.data.remote.FirebaseAutenticacionRepository
import cl.malditosnakamas.briska.autenticacion.domain.LoginUsuarioPassUseCase
import cl.malditosnakamas.briska.autenticacion.presentation.LoginUiState
import cl.malditosnakamas.briska.autenticacion.presentation.LoginViewModel
import cl.malditosnakamas.briska.autenticacion.presentation.LoginViewModelFactory
import cl.malditosnakamas.briska.databinding.FragmentLoginBinding
import cl.malditosnakamas.briska.utils.extensions.alert
import cl.malditosnakamas.componentart.dialog.LoadingProgressDialogFragment
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var progressDialog: LoadingProgressDialogFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentLoginBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupDependencies() {
        viewModelFactory =
            LoginViewModelFactory(
                LoginUsuarioPassUseCase(
                    FirebaseAutenticacionRepository(
                        FirebaseAuth.getInstance()
                    )
                )
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        progressDialog = LoadingProgressDialogFragment(childFragmentManager)
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
        progressDialog.show()
    }
}