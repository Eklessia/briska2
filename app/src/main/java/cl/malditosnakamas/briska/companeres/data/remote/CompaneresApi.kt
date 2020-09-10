package cl.malditosnakamas.briska.companeres.data.remote

import cl.malditosnakamas.briska.companeres.domain.Companere
import retrofit2.http.GET

interface CompaneresApi {

    @GET("companeres")
    suspend fun obtainCompaneres() : List<Companere>

}
