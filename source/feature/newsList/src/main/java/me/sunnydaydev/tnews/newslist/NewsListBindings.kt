package me.sunnydaydev.tnews.newslist

import me.sunnydaydev.mvvmkit.binding.RecyclerViewBindingsAdapter
import me.sunnydaydev.tnews.newslist.vm.NewsItemViewModel

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class NewsListBindings {

    val newsItemsMap = RecyclerViewBindingsAdapter.BindingMap(R.id.binding_newsitems_itemsmap)
            .map<NewsItemViewModel>(BR.vm, R.layout.newslist_item_layout)

}