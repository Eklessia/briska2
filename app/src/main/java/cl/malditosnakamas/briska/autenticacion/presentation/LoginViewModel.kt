package cl.malditosnakamas.briska.autenticacion.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.malditosnakamas.briska.autenticacion.domain.LoginUsuarioPassUseCase
import cl.malditosnakamas.briska.autenticacion.domain.UserAuth
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUsuarioPassUseCase: LoginUsuarioPassUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<LoginUiState>()

    fun getLiveData() = liveData

    fun doLogin(email: String, pass: String) {
        liveData.postValue(LoginUiState.Loading)
        viewModelScope.launch {
            try {
                val result = loginUsuarioPassUseCase.execute(email, pass)
                handleResult(result)
            } catch (exception: Exception) {
                handleError(exception)
            }
        }
    }

    private fun handleError(exception: Throwable) {
        liveData.postValue(LoginUiState.Error(exception))
    }

    private fun handleResult(result: UserAuth) {
        liveData.postValue(LoginUiState.Success(result))
    }
}