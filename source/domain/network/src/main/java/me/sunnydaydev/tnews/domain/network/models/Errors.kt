package me.sunnydaydev.tnews.domain.network.models

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

open class ApiError: Throwable()

class ApiRequestError(val code: String): ApiError()

class PayloadIsNullError: ApiError()