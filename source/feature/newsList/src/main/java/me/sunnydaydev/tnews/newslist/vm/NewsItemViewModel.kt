package me.sunnydaydev.tnews.newslist.vm

import android.text.TextUtils
import androidx.databinding.Bindable
import com.github.nitrico.lastadapter.StableId
import me.sunnydaydev.mvvmkit.observable.PureCommand
import me.sunnydaydev.tnews.coreui.viewModel.BaseVewModel
import me.sunnydaydev.tnews.domain.news.News
import me.sunnydaydev.mvvmkit.observable.bindable
import me.sunnydaydev.tnews.coreui.util.StableIdProvider
import me.sunnydaydev.tnews.newslist.NewsContentTransitionData
import me.sunnydaydev.tnews.newslist.NewsListRouter
import me.sunnydaydev.tnews.newslist.R
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class NewsItemViewModel(
        internal val news: News,
        private val stableIdProvider: StableIdProvider,
        private val router: NewsListRouter
): BaseVewModel(), StableId {

    override val stableId: Long get() = stableIdProvider[news.id]

    @get:Bindable val title by bindable(TextUtils.htmlEncode(news.text)!!)
    @get:Bindable val date by bindable(formatDate(news.publicationDate))
    @get:Bindable val icon by bindable(iconUrl(news.id))

    @get:Bindable val titleTransitionName by bindable("titleTransition${news.id}")

    @get:Bindable val titleTags by bindable(createTitleTags())

    val highlightCommand = PureCommand()

    fun onItemClicked() {
        router.openNews(NewsContentTransitionData(news.id, title))
    }

    private fun createTitleTags(): Map<Int, Any> = mapOf(
            R.id.transition_newslist_title to news.id
    )

    class Factory @Inject constructor(
            private val stableIdProvider: StableIdProvider,
            private val router: NewsListRouter
    ) {

        fun create(news: News): NewsItemViewModel =
                NewsItemViewModel(news, stableIdProvider, router)

    }

    companion object {

        private fun iconUrl(id: String) = "https://api.adorable.io/avatars/64/$id.png"

        private fun formatDate(date: Date): String =
                SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(date)

    }

}