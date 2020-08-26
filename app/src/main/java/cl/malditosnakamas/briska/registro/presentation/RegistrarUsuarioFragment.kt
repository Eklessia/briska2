package cl.malditosnakamas.briska.registro.presentation

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
import cl.malditosnakamas.briska.registro.domain.RegistroUsuarioRepository
import cl.malditosnakamas.briska.utils.extensions.alert
import com.google.firebase.auth.FirebaseAuth

class RegistrarUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {

    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var useCase: RegistrarUsuarioUseCase
    lateinit var repository: RegistroUsuarioRepository
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
        repository = FirebaseRegistroUsuarioRepository(FirebaseAuth.getInstance())
        useCase = RegistrarUsuarioUseCase(repository)
        viewModelFactory = RegistroViewModelFactory(useCase)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RegistroUsuarioViewModel::class.java)
    }

    private fun setupListener() {
        binding.apply {
            btnRegistrar.setOnClickListener {
                viewModel.registrarUsuario(obtenerValoresDeEditText())
            }
        }
    }

    private fun obtenerValoresDeEditText(): RegistroUsuario {
        return RegistroUsuario("m4rsh4ll D. t34ch", "1-9", "m4arsh4all.d.t34ch@mn.cl", "12345678")
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                state?.let { handleState(it) }
            }
        )
    }

    private fun handleState(state: RegistroUiState) {
        when(state){
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
    }

    private fun showRegistroExitoso() {
        alert("Registro OK :)")
    }

    private fun showLoading() {
        alert("Cargando")
    }


}