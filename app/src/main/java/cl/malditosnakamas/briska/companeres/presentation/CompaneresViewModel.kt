package cl.malditosnakamas.briska.companeres.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.malditosnakamas.briska.companeres.domain.Companere
import cl.malditosnakamas.briska.companeres.domain.ObtainCompaneresUseCase
import kotlinx.coroutines.launch

class CompaneresViewModel(
    private val companeresUseCase: ObtainCompaneresUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<CompaneresUiState>()

    fun getLiveData() = liveData

    fun obtainCompaneres() {
        liveData.postValue(CompaneresUiState.Loading)
        viewModelScope.launch {
            try {
                val result = companeresUseCase.execute()
                handleResult(result)
            } catch (e: Exception){
                handleError(e)
            }
        }
    }

    //TODO Analizar errores de negocio, por ahora no se diferencian los errores
    private fun handleError(e: Exception) {
        liveData.postValue(CompaneresUiState.ServerError)
    }

    private fun handleResult(result: List<Companere>) {
        if(result.isEmpty()){
            liveData.postValue(CompaneresUiState.EmptyList)
        }else{
            liveData.postValue(CompaneresUiState.Success(result))
        }
    }
}