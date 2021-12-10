

package com.xzy.load.manager.state

import com.xzy.load.manager.R
import com.xzy.load.manager.core.LoadCallback

class Timeout(private val timeoutResLayout: Int? = null) : LoadCallback() {

    override fun onCreateView(): Int {
        return timeoutResLayout ?: R.layout.layout_timeout_default
    }
}
