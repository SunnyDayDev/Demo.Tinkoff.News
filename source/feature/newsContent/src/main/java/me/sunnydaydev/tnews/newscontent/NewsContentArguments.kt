package me.sunnydaydev.tnews.newscontent

import android.os.Bundle
import me.sunnydaydev.tnews.coregeneral.util.bundleString

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal var Bundle.newsId: String by bundleString("news.content.id")

internal data class NewsContentArguments(
        val id: String
) {

    constructor(args: Bundle): this(args.newsId)

}