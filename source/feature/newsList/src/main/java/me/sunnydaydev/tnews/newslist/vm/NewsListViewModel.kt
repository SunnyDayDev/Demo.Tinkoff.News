package me.sunnydaydev.tnews.newslist.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.databinding.Bindable
import me.sunnydaydev.tnews.coregeneral.rx.defaultSchedulers
import me.sunnydaydev.tnews.coreui.viewModel.ViewModelState
import me.sunnydaydev.tnews.domain.news.News
import me.sunnydaydev.modernrx.*
import me.sunnydaydev.mvvmkit.observable.MVVMArrayList
import me.sunnydaydev.mvvmkit.observable.MVVMList
import me.sunnydaydev.mvvmkit.observable.bindable
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import me.sunnydaydev.tnews.coreui.viewModel.LifecycleVewModel
import me.sunnydaydev.tnews.newslist.NewsListInteractor
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 *
 * Contain business logic which interact within module.
 *
 */

internal class NewsListViewModel @Inject constructor(
        private val interactor: NewsListInteractor,
        private val itemFactory: NewsItemViewModel.Factory,
        viewLifeCycle: ViewLifeCycle
): LifecycleVewModel(viewLifeCycle) {

    @get:Bindable val items: MVVMList<NewsItemViewModel> = MVVMArrayList()

    @get:Bindable var state by bindable(ViewModelState.LOADING)

    private val startedScopeDisposable = DisposableBag(enabled = false)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStart() {

        startedScopeDisposable.enabled = true

        loadNews()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onViewStop() {
        startedScopeDisposable.enabled = false
    }

    fun onRefresh() {
        loadNews()
    }

    private fun loadNews() {

        state = ViewModelState.LOADING

        interactor.getNewsList()
                .defaultSchedulers()
                .subscribeIt(
                        onSuccess = ::handleNews,
                        onError = SimpleErrorHandler(false) {
                            state = ViewModelState.ERROR
                        }
                )

    }

    private fun handleNews(items: List<News>) {

        this.items.setAll(items.map(itemFactory::create))

        state = ViewModelState.CONTENT

    }

}