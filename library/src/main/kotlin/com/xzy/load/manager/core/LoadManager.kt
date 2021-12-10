

package com.xzy.load.manager.core

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner

internal var callbacks = HashSet<LoadCallback>()
internal lateinit var defaultLoadCallback: Class<out LoadCallback>
internal var animateTime: Long = 500L

fun View.observe(lifecycleOwner: LifecycleOwner, onReloadListener: ((View) -> Unit)?): LoadService {
    val targetContext = getTargetContext(this)
    return LoadService(lifecycleOwner, targetContext, onReloadListener)
}

internal fun getTargetContext(target: View): TargetContext {
    val contentParent = target.parent as ViewGroup
    val childIndex = contentParent.indexOfChild(target)
    contentParent.removeView(target)
    return TargetContext(contentParent, target, childIndex)
}

object LoadManager {
    fun install(inputCallBacks: HashSet<LoadCallback>): LoadManager {
        if (callbacks.isEmpty()) {
            inputCallBacks.forEach { callbacks.add(it) }
            return this
        } else {
            throw IllegalStateException("you have repeat call 'install' method")
        }
    }

    fun setDefaultCallback(callback: Class<out LoadCallback>) {
        defaultLoadCallback = callback
    }

    fun setAnimateTime(time: Long) {
        animateTime = time
    }
}
