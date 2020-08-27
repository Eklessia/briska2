package cl.malditosnakamas.briska.registro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.malditosnakamas.briska.R
import cl.malditosnakamas.briska.databinding.FragmentRegistroUsuarioBinding
import cl.malditosnakamas.briska.registro.data.remote.FirebaseRegistroUsuarioRepository
import cl.malditosnakamas.briska.registro.domain.RegistrarUsuarioUseCase
import cl.malditosnakamas.briska.registro.domain.RegistroUsuario
import cl.malditosnakamas.briska.registro.presentation.RegistroUiState
import cl.malditosnakamas.briska.registro.presentation.RegistroUsuarioViewModel
import cl.malditosnakamas.briska.registro.presentation.RegistroViewModelFactory
import cl.malditosnakamas.briska.utils.extensions.*
import com.google.firebase.auth.FirebaseAuth

class RegistrarUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {

    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var viewModel: RegistroUsuarioViewModel
    lateinit var viewModelFactory: RegistroViewModelFactory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentRegistroUsuarioBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupDependencies() {
        viewModelFactory =
            RegistroViewModelFactory(
                RegistrarUsuarioUseCase(
                    FirebaseRegistroUsuarioRepository(FirebaseAuth.getInstance())
                )
            )
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RegistroUsuarioViewModel::class.java)
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                state?.let { handleState(it) }
            }
        )
    }

    private fun setupListener() {
        binding.apply {
            btnRegistrar.setOnClickListener {
                if (isAllValidInputs()) {
                    viewModel.registrarUsuario(obtenerValoresDeEditText())
                }
            }

            btnVolver.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun isAllValidInputs(): Boolean {
        binding.apply {
            return etPass.isValidPassInput("Ingrese contraseña con 6 caracteres") ||
                    etEmail.isValidEmailInput("Ingrese un correo valido") ||
                    etRut.isValidRutInput("Ingrese un Rut valido") ||
                    etNombre.isValidNameInput("Ingrese un nombre valido")
        }
    }

    private fun obtenerValoresDeEditText(): RegistroUsuario {
        binding.apply {
            return RegistroUsuario(
                etNombre.text.toString(),
                etRut.text.toString(),
                etEmail.text.toString(),
                etPass.text.toString()
            )
        }
    }


    private fun handleState(state: RegistroUiState) {
        when (state) {
            is RegistroUiState.LoadingRegistroUiState -> showLoading()
            is RegistroUiState.SuccessRegistroUiState -> showRegistroExitoso()
            is RegistroUiState.InvalidEmailRegistroUiState -> showEmailRegistrado()
            is RegistroUiState.ErrorRegistroUiState -> showError()
        }
    }

    private fun showError() {
        alert("Error servidor")
    }

    private fun showEmailRegistrado() {
        alert("Email ya registrado")
        binding.etEmail.requestFocus()
    }

    private fun showRegistroExitoso() {
        alert("Registro OK :)")
    }

    private fun showLoading() {
        alert("Cargando")
    }


}