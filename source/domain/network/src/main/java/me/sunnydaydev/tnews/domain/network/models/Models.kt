package me.sunnydaydev.tnews.domain.network.models

import kotlinx.serialization.Serializable

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Serializable
data class NewsDto(
        val id: String,
        val name: String,
        val text: String,
        val publicationDate: PublicationDate,
        val bankInfoTypeId: Int
) {

    @Serializable
    data class PublicationDate(val milliseconds: Long)

}

@Serializable
data class NewsContentDto(
        val todo: Any // TODO: fill
)
