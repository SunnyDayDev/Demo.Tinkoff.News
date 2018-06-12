package me.sunnydaydev.tnews.newscontent

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.SpannedString
import androidx.core.text.HtmlCompat
import androidx.databinding.Bindable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import me.sunnydaydev.modernrx.*
import me.sunnydaydev.mvvmkit.OnBackPressedListener
import me.sunnydaydev.mvvmkit.observable.bindable
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import me.sunnydaydev.tnews.coregeneral.AppResources
import me.sunnydaydev.tnews.coregeneral.rx.defaultSchedulers
import me.sunnydaydev.tnews.coreui.viewModel.LifecycleVewModel
import me.sunnydaydev.tnews.coreui.viewModel.ViewModelState
import me.sunnydaydev.tnews.domain.news.NewsContent
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 *
 * Contain business logic which interact within module.
 *
 */

internal class NewsContentViewModel @Inject constructor(
        private val args: NewsContentParams,
        private val interactor: NewsContentInteractor,
        private val router: NewsContentRouter,
        viewLifeCycle: ViewLifeCycle
): LifecycleVewModel(viewLifeCycle), OnBackPressedListener {

    @get:Bindable var state by bindable(ViewModelState.LOADING)
    @get:Bindable var title by bindable(args.title)
    @get:Bindable var content: Spanned by bindable(SpannedString(""))

    @get:Bindable val titleTransitionName by bindable(args.titleTransitionName)

    private val contentDisposable = OptionalDisposable(true)

    private var hasContent = false

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStart() {
        if (hasContent) return

        loadNewsContent()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onViewStop() = contentDisposable.dispose()

    fun onRetry() {
        loadNewsContent()
    }

    override fun onBackPressed(): Boolean = router.exit().let { true }

    private fun loadNewsContent() {

        state = ViewModelState.LOADING

        interactor.getNewsContent(args.id)
                .defaultSchedulers()
                .subscribeIt(
                        onSuccess = ::handleNewsContent,
                        onError = SimpleErrorHandler(false, ::handleNewsContentError)
                )
                .disposeBy(contentDisposable)

    }

    private fun handleNewsContent(news: NewsContent) {

        hasContent = true
        title = news.title.text
        content = HtmlCompat.fromHtml(news.content, HtmlCompat.FROM_HTML_MODE_LEGACY)
        state = ViewModelState.CONTENT

    }

    private fun handleNewsContentError() {
        state = ViewModelState.ERROR
    }

}