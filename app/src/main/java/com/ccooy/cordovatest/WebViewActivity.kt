package com.ccooy.cordovatest

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import com.github.lzyzsd.jsbridge.DefaultHandler
import android.webkit.WebView
import com.google.gson.Gson
import android.content.Intent


class WebViewActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    var RESULT_CODE = 0

    var mUploadMessage: ValueCallback<Uri>? = null

    var mUploadMessageArray: ValueCallback<Array<Uri>>? = null

    internal class Location {
        var address: String? = null
    }

    internal class User {
        var name: String? = null
        var location: Location? = null
        var testStr: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        button.setOnClickListener {
            webView.callHandler("functionInJs", "data from Java") { data ->
                Log.i(TAG, "reponse data from js $data")
            }
//            pickFile()
        }
        webView.setDefaultHandler(DefaultHandler())
        webView.webChromeClient = object : WebChromeClient() {

            fun openFileChooser(uploadMsg: ValueCallback<Uri>, AcceptType: String, capture: String) {
                this.openFileChooser(uploadMsg)
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>, AcceptType: String) {
                this.openFileChooser(uploadMsg)
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
                mUploadMessage = uploadMsg
                pickFile()
            }

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: WebChromeClient.FileChooserParams
            ): Boolean {
                mUploadMessageArray = filePathCallback
                pickFile()
                return true
            }
        }
//        webView.loadUrl("file:///android_asset/demo.html")
        webView.loadUrl("http://192.168.1.57:8000/")

        webView.registerHandler("submitFromWeb") { data, function ->
            Log.i(TAG, "handler = submitFromWeb, data from web = $data")
            function.onCallBack("submitFromWeb exe, response data 中文 from Java")
        }

        val user = User()
        val location = Location()
        location.address = "SDU"
        user.location = location
        user.name = "大头鬼"

        webView.callHandler("functionInJs", Gson().toJson(user)) { }

        webView.send("hello")
    }

    fun pickFile() {
        val chooserIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooserIntent.type = "image/*"
        startActivityForResult(chooserIntent, RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == RESULT_CODE) {
            if (null == mUploadMessage && null == mUploadMessageArray) {
                return
            }
            if (null != mUploadMessage && null == mUploadMessageArray) {
                val result = if (intent == null || resultCode != RESULT_OK) null else intent.data
                mUploadMessage?.onReceiveValue(result)
                mUploadMessage = null
            }

            if (null == mUploadMessage && null != mUploadMessageArray) {
                val result = if (intent == null || resultCode != RESULT_OK) null else intent.data
                result?.let {
                    mUploadMessageArray?.onReceiveValue(arrayOf(it))
                }
                mUploadMessageArray = null
            }

        }
    }
}