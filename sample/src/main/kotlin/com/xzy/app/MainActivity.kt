package com.xzy.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xzy.app.databinding.ActivityMainBinding
import com.xzy.load.manager.core.LoadCallback
import com.xzy.load.manager.core.LoadManager
import com.xzy.load.manager.state.Empty
import com.xzy.load.manager.state.Error
import com.xzy.load.manager.state.Loading
import com.xzy.load.manager.state.Success
import com.xzy.load.manager.state.Timeout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onResume() {
        super.onResume()
        System.gc()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadManager()
        binding.content.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }

    private fun initLoadManager() {
        val inputCallBacks = hashSetOf(
            // 这里可以传入自定义的布局
            Loading(),
            Timeout(),
            Error(),
            Empty(),
            Custom() // 这个是自定义的事件
        )

        LoadManager.install(inputCallBacks)
            .apply {
                setDefaultCallback(Success::class.java)
                setAnimateTime(300)
            }
    }
}

class Custom : LoadCallback() {
    override fun onCreateView(): Int {
        return R.layout.activity_custom
    }
}

