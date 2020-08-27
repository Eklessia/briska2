package cl.malditosnakamas.briska.session.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.malditosnakamas.briska.session.domain.ObtenerSessionUseCase
import cl.malditosnakamas.briska.session.domain.Session
import kotlinx.coroutines.launch

class SessionViewModel(
    private val sessionUseCase: ObtenerSessionUseCase
) : ViewModel() {
    private val liveData = MutableLiveData<SessionUiState>()
    fun getLiveData() = liveData

    fun obtenerSession(){
        liveData.postValue(SessionUiState.Loading)
        viewModelScope.launch {
            try{
                val result = sessionUseCase.excecute()
                handleResult(result)
            }catch (exception: Exception){
                handleError()
            }
        }
    }

    private fun handleResult(session: Session) {
        if(session.isValid){
            liveData.postValue(SessionUiState.SuccessSession)
        }else{
            liveData.postValue(SessionUiState.InvalidSession)
        }
    }

    private fun handleError() {
        liveData.postValue(SessionUiState.InvalidSession)
    }
}