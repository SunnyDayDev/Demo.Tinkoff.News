package me.sunnydaydev.tnews.domain.news

import java.util.*

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

data class News(
        val id: String,
        val name: String,
        val text: String,
        val publicationDate: Date,
        val bankInfoTypeId: Int
)

data class NewsContent(
        val title: News,
        val creationDate: Date,
        val lastModificationDate: Date,
        val content: String,
        val bankInfoTypeId: Int,
        val typeId: String
)