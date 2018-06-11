package me.sunnydaydev.tnews.domain.news.mapper

import me.sunnydaydev.tnews.coregeneral.di.WeakLazy
import me.sunnydaydev.tnews.coregeneral.util.Mapper
import me.sunnydaydev.tnews.domain.network.models.NewsContentDto
import me.sunnydaydev.tnews.domain.network.models.NewsDto
import me.sunnydaydev.tnews.domain.news.News
import me.sunnydaydev.tnews.domain.news.NewsContent
import me.sunnydaydev.tnews.domain.news.db.NewsEntity
import java.util.*
import javax.inject.Inject

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class NewsMapperFactory @Inject constructor(
        private val newsDtoToEntityProvider: WeakLazy<NewsDtoToEntityMapper>,
        private val newsEntityToPlainProvider: WeakLazy<NewsEntityToPlainMapper>,
        private val newsContentDtoToPlainProvider: WeakLazy<NewsContentDtoToPlainMapper>
) {

    val newsDtoToEntity: Mapper<NewsDto, NewsEntity> get() = newsDtoToEntityProvider.get()

    val newsEntityToPlain: Mapper<NewsEntity, News> get() = newsEntityToPlainProvider.get()

    val newsContentDtoToPlain: Mapper<NewsContentDto, NewsContent> get() =
            newsContentDtoToPlainProvider.get()

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

internal class NewsDtoToPlainMapper @Inject constructor(): Mapper<NewsDto, News> {

    override fun map(source: NewsDto) = News(
            id = source.id,
            name = source.name,
            text = source.text,
            publicationDate = Date(source.publicationDate.milliseconds),
            bankInfoTypeId = source.bankInfoTypeId
    )

}

internal class NewsContentDtoToPlainMapper @Inject constructor(
        private val newsMapper: NewsDtoToPlainMapper
): Mapper<NewsContentDto, NewsContent> {

    override fun map(source: NewsContentDto) = NewsContent(
            title = newsMapper.map(source.title),
            creationDate = Date(source.creationDate.milliseconds),
            lastModificationDate = Date(source.lastModificationDate.milliseconds),
            content = source.content,
            bankInfoTypeId = source.bankInfoTypeId,
            typeId = source.typeId
    )

}