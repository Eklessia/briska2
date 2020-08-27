package cl.malditosnakamas.briska.session.presentation

import cl.malditosnakamas.briska.session.domain.Session

sealed class SessionUiState {
    object Loading : SessionUiState()
    object SuccessSession : SessionUiState()
    object InvalidSession : SessionUiState()
}