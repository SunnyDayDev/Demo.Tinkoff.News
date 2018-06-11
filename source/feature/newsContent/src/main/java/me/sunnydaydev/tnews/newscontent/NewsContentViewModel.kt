package me.sunnydaydev.tnews.newscontent

import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import android.text.SpannedString
import androidx.databinding.Bindable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import me.sunnydaydev.modernrx.*
import me.sunnydaydev.mvvmkit.OnBackPressedListener
import me.sunnydaydev.mvvmkit.observable.bindable
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import me.sunnydaydev.tnews.coregeneral.rx.defaultSchedulers
import me.sunnydaydev.tnews.coreui.viewModel.LifecycleVewModel
import me.sunnydaydev.tnews.coreui.viewModel.ViewModelState
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
    @get:Bindable var title by bindable("")
    @get:Bindable var content: Spanned by bindable(SpannedString(""))

    @get:Bindable val titleTransitionName by bindable(args.titleTransitionName)

    private val startedScopeDisposable = DisposableBag(enabled = false)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStart() {

        startedScopeDisposable.enabled = true

        loadNewsContent()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onViewStop() {
        startedScopeDisposable.enabled = false
    }

    fun onRetry() {
        loadNewsContent()
    }

    override fun onBackPressed(): Boolean = router.exit().let { true }

    private fun loadNewsContent() {
        interactor.getNewsContent(args.id)
                .defaultSchedulers()
                .subscribeIt {
                    title = it.title.text
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        content = Html.fromHtml(it.content, 0)
                    } else {
                        @Suppress("DEPRECATION")
                        content = Html.fromHtml(it.content)
                    }
                }
    }

}