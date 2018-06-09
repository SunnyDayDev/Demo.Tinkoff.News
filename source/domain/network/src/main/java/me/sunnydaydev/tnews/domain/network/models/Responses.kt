package me.sunnydaydev.tnews.domain.network.models

import io.reactivex.Single
import kotlinx.serialization.Serializable

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal fun <R: Response<D>, D> Single<R>.checkResponse(): Single<D> = flatMap {
    when {
        it.resultCode != "OK" -> Single.error(ApiRequestError(it.resultCode))
        it.payload == null -> Single.error(PayloadIsNullError())
        else -> Single.just(it.payload!!)
    }
}

internal interface Response<T> {

    val payload: T?
    val resultCode: String
    val trackingId: Long

}

@Serializable
internal data class NewsContentResponse(
        override val payload: NewsContentDto?,
        override val resultCode: String,
        override val trackingId: Long
): Response<NewsContentDto>

@Serializable
internal data class NewsResponse(
        override val payload: List<NewsDto>?,
        override val resultCode: String,
        override val trackingId: Long
): Response<List<NewsDto>>