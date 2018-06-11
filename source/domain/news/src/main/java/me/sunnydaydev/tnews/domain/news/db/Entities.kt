package me.sunnydaydev.tnews.domain.news.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Entity(tableName = "news")
internal data class NewsEntity(
        @PrimaryKey
        val id: String,
        val name: String,
        val text: String,
        val publicationDate: Long,
        val bankInfoTypeId: Int
)