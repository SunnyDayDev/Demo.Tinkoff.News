package me.sunnydaydev.tnews.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import me.sunnydaydev.tnews.splash.di.SplashComponent
import me.sunnydaydev.tnews.splash.di.Injection
import me.sunnydaydev.tnews.coregeneral.di.RequirementsComponentProvider
import me.sunnydaydev.tnews.coreui.MVVMFragment
import me.sunnydaydev.tnews.coreui.viewModel.BaseVewModel
import me.sunnydaydev.mvvmkit.util.inflateBinding
import me.sunnydaydev.mvvmkit.viewModel.get
import me.sunnydaydev.tnews.splash.databinding.SplashFragmentBinding
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class SplashFragment: MVVMFragment<SplashFragmentBinding>() {

    @Inject
    internal lateinit var injection: Injection

    override val viewModelVariableId = BR.vm

    override val viewModelFactory: ViewModelProvider.Factory by lazy { injection.vmFactory }

    override fun getViewModel(provider: ViewModelProvider): BaseVewModel =
            provider[SplashViewModel::class]

    override fun inject(provider: RequirementsComponentProvider) {

        SplashComponent.Initializer
                .init(provider.getComponentRequirements())
                .inject(this)

    }

    override fun onCreateBinding(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflateBinding<SplashFragmentBinding>(R.layout.splash_fragment, container)

}