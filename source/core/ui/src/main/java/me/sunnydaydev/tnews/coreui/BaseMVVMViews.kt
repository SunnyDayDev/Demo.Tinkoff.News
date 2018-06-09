package me.sunnydaydev.tnews.coreui

import androidx.databinding.ViewDataBinding
import me.sunnydaydev.tnews.coregeneral.di.RequirementsComponentProvider
import me.sunnydaydev.mvvmkit.MVVMActivity
import me.sunnydaydev.mvvmkit.MVVMFragment
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

abstract class MVVMActivity<Binding: ViewDataBinding>: MVVMActivity<Binding>() {

    @Inject
    override lateinit var viewLifeCycle: ViewLifeCycle

    override fun proceedInjection() {

        val requirementsProvider = parent as? RequirementsComponentProvider
                ?: applicationContext as RequirementsComponentProvider

        inject(requirementsProvider)

    }

    protected abstract fun inject(provider: RequirementsComponentProvider)

}

abstract class MVVMFragment<Binding: ViewDataBinding>: MVVMFragment<Binding>()  {

    @Inject
    override lateinit var viewLifeCycle: ViewLifeCycle

    override fun proceedInjection() {

        val context = context ?: return

        val requirementsProvider = parentFragment as? RequirementsComponentProvider
                ?: activity as? RequirementsComponentProvider
                ?: context.applicationContext as RequirementsComponentProvider

        inject(requirementsProvider)

    }

    protected abstract fun inject(provider: RequirementsComponentProvider)

}