package com.ccooy.cordovatest

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import org.apache.cordova.CordovaActivity

@Route(path = "/test/2")
class MainActivity1 : CordovaActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        // Set by <content src="index.html" /> in config.xml
        loadUrl("http://192.168.1.57:8000/")

        val runnable = Runnable {
            try {
                Thread.sleep(3000)
                runOnUiThread { loadUrl("javascript:showAlert(\"你好\")") }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        val thread = Thread(runnable)
        thread.start()
    }
}
