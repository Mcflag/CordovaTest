package com.ccooy.cordovatest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "/test/2")
class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        button.setOnClickListener {
            ARouter.getInstance().build("/test/2").navigation()
        }
        val intent = intent
        val key1 = intent.getStringExtra("key1")
        val key2 = intent.getStringExtra("key2")
        if (key1 != null || key2 != null) {
            Log.d("TagMainActivity", "$key1 : $key2")
        }
    }

}