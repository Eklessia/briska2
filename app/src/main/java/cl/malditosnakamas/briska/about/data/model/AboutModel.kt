package cl.malditosnakamas.briska.about.data.model

import com.google.gson.annotations.SerializedName

data class AboutModel(
    val version: String,
    @SerializedName("name_app")
    val name: String,
    val author: String,
    val company : String
)