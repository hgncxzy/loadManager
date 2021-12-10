package com.xzy.app

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xzy.app.databinding.ActivityMain2Binding
import com.xzy.load.manager.core.LoadService
import com.xzy.load.manager.core.observe
import com.xzy.load.manager.state.Empty
import com.xzy.load.manager.state.Loading
import com.xzy.load.manager.state.Timeout

import kotlin.concurrent.thread

class Main2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var finish = false
    private lateinit var loadService: LoadService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        loadService = binding.container.observe(this, {}).apply {
            Log.d("load-manager", "observe>>>>>>>>>>>>>>>>>>>>>>")
            Toast.makeText(this@Main2Activity, "reload", Toast.LENGTH_LONG).show()
        }
        val time = 1000L

        thread {
            repeat(3) {
                if (!finish) {
                    with(binding.container) {
                        (parent as ViewGroup).postDelayed({ loadService.notify(Loading()) }, time)
                        Thread.sleep(time)
                        (parent as ViewGroup).postDelayed({ loadService.notify(Custom()) }, time)
                        Thread.sleep(time)
                        (parent as ViewGroup).postDelayed({ loadService.notify(Timeout()) }, time)
                        Thread.sleep(time)
                        (parent as ViewGroup).postDelayed({ loadService.notify(Throwable()) }, time)
                        Thread.sleep(time)
                        (parent as ViewGroup).postDelayed({ loadService.notify(Empty()) }, time)
                        Thread.sleep(time)
                        (parent as ViewGroup).postDelayed({ loadService.showSuccess() }, time)
                        Thread.sleep(time)
                        Log.d("load-manager", "doing...")
                    }
                } else {
                    return@repeat
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish = true
    }
}
