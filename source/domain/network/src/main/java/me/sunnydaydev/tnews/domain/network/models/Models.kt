package me.sunnydaydev.tnews.domain.network.models

import kotlinx.serialization.Serializable

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Serializable
data class DateDto(val milliseconds: Long)

@Serializable
data class NewsDto(
        val id: String,
        val name: String,
        val text: String,
        val publicationDate: DateDto,
        val bankInfoTypeId: Int
)

@Serializable
data class NewsContentDto(
    val title: NewsDto,
    val creationDate: DateDto,
    val lastModificationDate: DateDto,
    val content: String,
    val bankInfoTypeId: Int,
    val typeId: String
)
