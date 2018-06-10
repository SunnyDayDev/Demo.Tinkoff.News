package me.sunnydaydev.tnews.newscontent

import androidx.databinding.Bindable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import me.sunnydaydev.modernrx.*
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
        private val args: NewsContentArguments,
        private val interactor: NewsContentInteractor,
        viewLifeCycle: ViewLifeCycle
): LifecycleVewModel(viewLifeCycle) {

    @get:Bindable var state by bindable(ViewModelState.LOADING)
    @get:Bindable var content by bindable("")

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

    private fun loadNewsContent() {
        interactor.getNewsContent(args.id)
                .defaultSchedulers()
                .subscribeIt { content = it.content }
    }

}