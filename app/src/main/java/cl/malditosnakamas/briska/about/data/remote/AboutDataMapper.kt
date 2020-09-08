package cl.malditosnakamas.briska.about.data.remote

import cl.malditosnakamas.briska.about.data.model.AboutModel
import cl.malditosnakamas.briska.about.domain.About

class AboutDataMapper {
    fun mapToDomain(aboutModel: AboutModel): About {
        aboutModel.apply {
            return About(version, name, author, company)
        }
    }
}