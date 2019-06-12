package com.ccooy.cordovatest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log


@Route(path = "/test/1")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            ARouter.getInstance().build("/test/2").navigation()
        }
        button1.setOnClickListener {
            ARouter.getInstance().build("/test/3").navigation()
        }
        val intent = intent
        val key1 = intent.getStringExtra("key1")
        val key2 = intent.getStringExtra("key2")
        if (key1 != null || key2 != null) {
            Log.d("TagMainActivity", "$key1 : $key2")
        }
    }

}
