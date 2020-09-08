package cl.malditosnakamas.briska.about.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.malditosnakamas.briska.about.domain.About
import cl.malditosnakamas.briska.about.domain.ObtainAboutUseCase
import kotlinx.coroutines.launch

class AboutViewModel(
    private val obtainAboutUseCase: ObtainAboutUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<About?>()
    fun getLiveData() = liveData

    fun obtainAbout(){
        viewModelScope.launch {
            try {
                val result = obtainAboutUseCase.excecute()
                liveData.postValue(result)
            }catch (e: Exception){
                liveData.postValue(null)
            }
        }
    }

}