package me.sunnydaydev.tnews.domain.news.mapper

import me.sunnydaydev.tnews.coregeneral.di.WeakLazy
import me.sunnydaydev.tnews.coregeneral.util.Mapper
import me.sunnydaydev.tnews.domain.network.models.NewsDto
import me.sunnydaydev.tnews.domain.news.News
import me.sunnydaydev.tnews.domain.news.db.NewsEntity
import java.util.*
import javax.inject.Inject

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class NewsMapperFactory @Inject constructor(
        private val dtoToEntityMapper: WeakLazy<NewsDtoToEntityMapper>,
        private val entityToPlainMapper: WeakLazy<NewsEntityToPlainMapper>
) {

    val dtoToEntity: Mapper<NewsDto, NewsEntity> get() = dtoToEntityMapper.get()

    val entityToPlain: Mapper<NewsEntity, News> get() = entityToPlainMapper.get()

}

internal class NewsDtoToEntityMapper @Inject constructor(): Mapper<NewsDto, NewsEntity> {

    override fun map(source: NewsDto) = NewsEntity(
            id = source.id,
            name = source.name,
            text = source.text,
            publicationDate = source.publicationDate.milliseconds,
            bankInfoTypeId = source.bankInfoTypeId
    )

}

internal class NewsEntityToPlainMapper @Inject constructor(): Mapper<NewsEntity, News> {

    override fun map(source: NewsEntity) = News(
            id = source.id,
            name = source.name,
            text = source.text,
            publicationDate = Date(source.publicationDate),
            bankInfoTypeId = source.bankInfoTypeId
    )

}