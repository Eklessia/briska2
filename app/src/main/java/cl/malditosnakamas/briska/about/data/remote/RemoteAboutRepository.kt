package cl.malditosnakamas.briska.about.data.remote

import cl.malditosnakamas.briska.about.domain.About
import cl.malditosnakamas.briska.about.domain.AboutRepository

class RemoteAboutRepository(
    private val aboutApi: AboutApi,
    private val aboutDataMapper: AboutDataMapper
) : AboutRepository {
    override suspend fun get(): About {
        return aboutDataMapper.mapToDomain(aboutApi.obtainAbout())
    }
}