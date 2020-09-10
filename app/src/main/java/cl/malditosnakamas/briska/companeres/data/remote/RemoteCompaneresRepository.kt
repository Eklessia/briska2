package cl.malditosnakamas.briska.companeres.data.remote

import cl.malditosnakamas.briska.companeres.domain.Companere
import cl.malditosnakamas.briska.companeres.domain.CompaneresRepository

class RemoteCompaneresRepository(
    private val companeresApi: CompaneresApi
) : CompaneresRepository {

    override suspend fun getAll(): List<Companere> {
        return companeresApi.obtainCompaneres()
    }
}