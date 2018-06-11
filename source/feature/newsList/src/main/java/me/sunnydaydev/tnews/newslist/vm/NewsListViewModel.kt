package me.sunnydaydev.tnews.newslist.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.databinding.Bindable
import me.sunnydaydev.tnews.coregeneral.rx.defaultSchedulers
import me.sunnydaydev.tnews.coreui.viewModel.ViewModelState
import me.sunnydaydev.tnews.domain.news.News
import me.sunnydaydev.modernrx.*
import me.sunnydaydev.mvvmkit.OnBackPressedListener
import me.sunnydaydev.mvvmkit.observable.Command
import me.sunnydaydev.mvvmkit.observable.MVVMList
import me.sunnydaydev.mvvmkit.observable.SortedMVVMList
import me.sunnydaydev.mvvmkit.observable.bindable
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import me.sunnydaydev.tnews.coregeneral.AppResources
import me.sunnydaydev.tnews.coregeneral.Toaster
import me.sunnydaydev.tnews.coreui.viewModel.LifecycleVewModel
import me.sunnydaydev.tnews.newslist.NewsListInteractor
import me.sunnydaydev.tnews.newslist.NewsListRouter
import me.sunnydaydev.tnews.newslist.R
import javax.inject.Inject
import kotlin.Comparator

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
        private val toaster: Toaster,
        private val res: AppResources,
        private val router: NewsListRouter,
        viewLifeCycle: ViewLifeCycle
): LifecycleVewModel(viewLifeCycle), OnBackPressedListener {

    private val newsComparator = createNewsComparator()
    private val viewModelsComparator = createViewModelsComparator()

    @get:Bindable val items: MVVMList<NewsItemViewModel> = SortedMVVMList(viewModelsComparator)
    @get:Bindable var state by bindable(ViewModelState.LOADING)

    val scrollToPosition = Command<Int>()

    private val updateNewsDisposable = OptionalDisposable(autoDispose = true)

    private var newsUpdated = false

    init {

        interactor.getNews()
                .defaultSchedulers()
                .subscribeIt(onNext = ::handleNews)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStart() {

        if (!newsUpdated) {
            updateNews()
        }

    }

    fun onRefresh() {
        updateNews()
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    private fun updateNews() {

        state = ViewModelState.LOADING

        interactor.updateNews()
                .doOnComplete { newsUpdated = true }
                .defaultSchedulers()
                .subscribeIt(onError = SimpleErrorHandler(false, ::onNewsUpdateError))
                .disposeBy(updateNewsDisposable)

    }

    private fun handleNews(data: List<News>) {

        state = ViewModelState.CONTENT

        val nonPresented = data
                .filterNot(::isAlreadyPresent)
                .map(itemFactory::create)

        if (nonPresented.isEmpty()) return

        val wasEmpty = this.items.isEmpty()

        items.addAll(nonPresented)

        if (!wasEmpty) {
            nonPresented.forEach { it.highlightCommand.fire() }
        }

        val topNewElementPosition = nonPresented
                .map { this.items.indexOf(it) }
                .min() ?: 0

        scrollToPosition.fire(topNewElementPosition)

    }

    private fun isAlreadyPresent(news: News) =
            items.binarySearch { newsComparator.compare(it.news, news) } >= 0

    private fun onNewsUpdateError() {

        if (items.isEmpty()) {
            state = ViewModelState.ERROR
        } else {
            state = ViewModelState.CONTENT
            toaster.makeToast(res.strings[R.string.prompt_error_happen])
        }

    }

    private fun createNewsComparator() = Comparator<News> { p0, p1 ->
        val d0 = p0.publicationDate
        val d1 = p1.publicationDate
        when {
            d0.before(d1) -> 1
            d1.before(d0) -> -1
            else -> p0.id.compareTo(p1.id)
        }
    }

    private fun createViewModelsComparator(): Comparator<NewsItemViewModel> {
        return Comparator { p0, p1 ->
            newsComparator.compare(p0.news, p1.news)
        }
    }

}