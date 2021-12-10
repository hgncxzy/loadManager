

package com.xzy.load.manager.state

import com.xzy.load.manager.R
import com.xzy.load.manager.core.LoadCallback

class Error(private val errorResLayout: Int? = null) : LoadCallback() {
    override fun onCreateView(): Int {
        return errorResLayout ?: R.layout.layout_error_default
    }
}
