package com.ccooy.cordovatest;

import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class SpeechOFFSynthesize extends CordovaPlugin {
    public void speak(String content) {
        Log.e("SpeechOFFSynthesize", content);
    }

    public void router(String content, String arg1, String arg2) {
        ARouter.getInstance().build(content)
                .withString("key1", arg1)
                .withString("key2", arg2)
                .navigation();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("speak".equals(action)) {
            //speechSynthesize
            String content = args.getString(0);
            speak(content);
            callbackContext.success("finish");//如果不调用success回调，则js中successCallback不会执行
            return true;
        } else if ("router".equals(action)) {
            String content = args.getString(0);
            String args1 = args.getString(1);
            String args2 = args.getString(2);
            router(content, args1, args2);
            callbackContext.success("finish");
        }
        return false;
    }
}
