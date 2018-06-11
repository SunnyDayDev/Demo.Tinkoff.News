package me.sunnydaydev.tnews.newslist

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NewsListRouter {

    fun openNews(data: NewsContentTransitionData)

    fun exit()

}

data class NewsContentTransitionData(val id: String, val title: String)