

package com.xzy.load.manager.core

import android.view.View
import android.view.ViewGroup

internal data class TargetContext(
    var contentParent: ViewGroup?,
    var target: View?,
    val childIndex: Int
)
