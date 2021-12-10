

package com.xzy.load.manager.state

import com.xzy.load.manager.R
import com.xzy.load.manager.core.LoadCallback

class Loading(private val loadingResLayout: Int? = null) : LoadCallback() {

    override fun onReloadEvent(): Boolean {
        return true
    }

    override fun onCreateView(): Int {
        return loadingResLayout ?: R.layout.layout_loading_default
    }
}
