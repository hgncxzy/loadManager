

package com.xzy.load.manager.state

import com.xzy.load.manager.R
import com.xzy.load.manager.core.LoadCallback

class Empty(private val emptyResLayout: Int? = null) : LoadCallback() {

    override fun onCreateView(): Int {
        return emptyResLayout ?: R.layout.layout_empty_default
    }
}
