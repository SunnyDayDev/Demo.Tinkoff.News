package me.sunnydaydev.tnews.newscontent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentActivity
import me.sunnydaydev.tnews.newscontent.di.NewsContentComponent
import me.sunnydaydev.tnews.newscontent.di.Injection
import me.sunnydaydev.tnews.coregeneral.di.RequirementsComponentProvider
import me.sunnydaydev.tnews.coreui.MVVMFragment
import me.sunnydaydev.tnews.coreui.viewModel.BaseVewModel
import me.sunnydaydev.mvvmkit.util.inflateBinding
import me.sunnydaydev.mvvmkit.viewModel.get
import me.sunnydaydev.tnews.coreui.util.ThemedAttributeProvider
import me.sunnydaydev.tnews.newscontent.databinding.NewscontentFragmentBinding
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class NewsContentFragment: MVVMFragment<NewscontentFragmentBinding>() {

    @Inject
    internal lateinit var injection: Injection

    override val viewModelVariableId = BR.vm

    override val viewModelFactory: ViewModelProvider.Factory by lazy { injection.vmFactory }

    override fun getViewModel(provider: ViewModelProvider): BaseVewModel =
            provider[NewsContentViewModel::class]

    override fun inject(provider: RequirementsComponentProvider) {

        val args = NewsContentParams(arguments!!)

        val themedAttributeProvider = ThemedAttributeProvider(activity!!.theme, context!!)
        NewsContentComponent.Initializer
                .init(provider.getComponentRequirements(), args, themedAttributeProvider)
                .inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateBinding(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflateBinding<NewscontentFragmentBinding>(R.layout.newscontent_fragment, container)
            .also {
                with(activity!!) {
                    it.root.doOnPreDraw { supportStartPostponedEnterTransition() }
                    this as? AppCompatActivity ?: return@with
                    setSupportActionBar(it.toolbar)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }

}