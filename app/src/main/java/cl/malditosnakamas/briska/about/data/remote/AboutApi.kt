package cl.malditosnakamas.briska.about.data.remote

import cl.malditosnakamas.briska.about.data.model.AboutModel
import retrofit2.http.GET

interface AboutApi {
    @GET("about")
    suspend fun obtainAbout() : AboutModel
}