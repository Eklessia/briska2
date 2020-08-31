package cl.malditosnakamas.briska.briskeros.presentation

import cl.malditosnakamas.briska.briskeros.domain.Briskero

sealed class BriskeroUiState{
    object Loading : BriskeroUiState()
    data class SuccessObtainBriskeros(val briskeros: List<Briskero>) : BriskeroUiState()
    object EmptyListBriskeros : BriskeroUiState()
    object ServerErrorBriskeros : BriskeroUiState()
    object NoInternetErrorBriskeros : BriskeroUiState()
}