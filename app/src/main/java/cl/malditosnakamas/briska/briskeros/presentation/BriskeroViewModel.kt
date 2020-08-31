package cl.malditosnakamas.briska.briskeros.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.malditosnakamas.briska.briskeros.domain.Briskero
import cl.malditosnakamas.briska.briskeros.domain.ObtenerBriskerosUseCase
import cl.malditosnakamas.briska.registro.domain.RegistrarUsuarioUseCase
import cl.malditosnakamas.briska.session.domain.ObtenerSessionUseCase
import cl.malditosnakamas.briska.session.domain.Session
import kotlinx.coroutines.launch
import java.lang.Exception

class BriskeroViewModel(
    private val obtenerBriskerosUseCase: ObtenerBriskerosUseCase,
    private val registrarUsuarioUseCase: RegistrarUsuarioUseCase
) : ViewModel(){

    private val liveData = MutableLiveData<BriskeroUiState>()

    fun getLiveData() = liveData

    fun obtenerBriskeros(){
        viewModelScope.launch {
            liveData.postValue(BriskeroUiState.Loading)
            try {
                handleResult(
                    obtenerBriskerosUseCase.execute()
                )
            }catch (exception :Exception){
                handleExeption(exception)
            }
        }
    }

    private fun handleResult(result: List<Briskero>) {
        if(result.isNotEmpty()){
            liveData.postValue(BriskeroUiState.SuccessObtainBriskeros(result))
        }else{
            liveData.postValue(BriskeroUiState.EmptyListBriskeros)
        }
    }


    private fun handleExeption(exception: Exception) {
        liveData.postValue(BriskeroUiState.ServerErrorBriskeros)
    }
}