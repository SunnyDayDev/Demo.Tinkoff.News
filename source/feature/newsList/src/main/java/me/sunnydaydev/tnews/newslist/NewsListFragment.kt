package me.sunnydaydev.tnews.newslist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import me.sunnydaydev.tnews.newslist.di.NewsListComponent
import me.sunnydaydev.tnews.newslist.di.Injection
import me.sunnydaydev.tnews.coregeneral.di.RequirementsComponentProvider
import me.sunnydaydev.tnews.coreui.MVVMFragment
import me.sunnydaydev.tnews.coreui.viewModel.BaseVewModel
import me.sunnydaydev.mvvmkit.util.inflateBinding
import me.sunnydaydev.mvvmkit.viewModel.get
import me.sunnydaydev.tnews.newslist.databinding.NewslistFragmentBinding
import me.sunnydaydev.tnews.newslist.vm.NewsListViewModel
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class NewsListFragment: MVVMFragment<NewslistFragmentBinding>() {

    @Inject
    internal lateinit var injection: Injection

    override val viewModelVariableId = BR.vm

    override val viewModelFactory: ViewModelProvider.Factory by lazy { injection.vmFactory }

    override fun getViewModel(provider: ViewModelProvider): BaseVewModel =
            provider[NewsListViewModel::class]

    override fun inject(provider: RequirementsComponentProvider) {
        NewsListComponent.Initializer.init(provider.getComponentRequirements())
                .inject(this)
    }

    override fun onCreateBinding(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflateBinding<NewslistFragmentBinding>(R.layout.newslist_fragment, container)
            .also { it.bindings = NewsListBindings() }

}