package me.sunnydaydev.tnews.coreui.viewModel

import androidx.annotation.CallSuper
import me.sunnydaydev.modernrx.*
import me.sunnydaydev.mvvmkit.viewModel.MVVMViewModel

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

abstract class BaseVewModel: MVVMViewModel(), ModernRx {

    private val disposerBag = DisposableBag()

    final override val modernRxDisposer: ModernRx.Disposer = ModernRx.Disposer(disposerBag)

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposerBag.enabled = false
    }

}