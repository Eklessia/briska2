package cl.malditosnakamas.briska.companeres.presentation

import cl.malditosnakamas.briska.companeres.domain.Companere

sealed class CompaneresUiState(
    open val result: List<Companere>? = null,
    open val errorMessage: String? = null
    ) {
    object Loading : CompaneresUiState()
    object EmptyList : CompaneresUiState()
    data class Success(override val result: List<Companere>?) : CompaneresUiState(result)
    object ServerError : CompaneresUiState()
    data class BussinesError(override val errorMessage: String) : CompaneresUiState(errorMessage = errorMessage)
}
