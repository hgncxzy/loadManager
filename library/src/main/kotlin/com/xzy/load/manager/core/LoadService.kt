

package com.xzy.load.manager.core

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.xzy.load.manager.inter.INetError
import com.xzy.load.manager.inter.INetTimeout
import com.xzy.load.manager.state.Error
import com.xzy.load.manager.state.Success
import com.xzy.load.manager.state.Timeout

class LoadService internal constructor(
    lifecycleOwner: LifecycleOwner,
    targetContext: TargetContext,
    onReloadListener: ((View) -> Unit)?
) {

    private var loadLayout: LoadLayout? =
        LoadLayout(targetContext.contentParent!!.context, onReloadListener)

    init {
        targetContext.contentParent?.addView(
            loadLayout,
            targetContext.childIndex,
            targetContext.target?.layoutParams
        )
        loadLayout?.installSuccessLayout(Success(targetContext.target!!))
        callbacks.forEach { loadLayout?.installCallbackLayout(it) }
        showCallback(defaultLoadCallback)

        lifecycleOwner.lifecycle.addObserver(
            object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                fun onStop() {
                    loadLayout = null
                    targetContext.contentParent = null
                    targetContext.target = null
                }
            }
        )
    }

    fun showSuccess() {
        showCallback(Success::class.java)
    }

    private fun showCallback(loadCallback: Class<out LoadCallback>) {
        loadLayout?.showCallback(loadCallback)
    }

    fun notify(event: Any) {
        when (event) {
            is INetTimeout -> notifyTimeout()
            is Throwable -> notifyError()
            is INetError -> notifyError()
            is LoadCallback -> notifyLoad(event)
            else -> throw IllegalArgumentException("非法参数：$event")
        }
    }

    private fun notifyLoad(event: LoadCallback) {
        showCallback(event::class.java)
    }

    private fun notifyTimeout() {
        showCallback(Timeout::class.java)
    }

    private fun notifyError() {
        showCallback(Error::class.java)
    }
}
