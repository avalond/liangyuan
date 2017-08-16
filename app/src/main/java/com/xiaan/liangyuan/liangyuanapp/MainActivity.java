package com.xiaan.liangyuan.liangyuanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.xiaan.liangyuan.liangyuanapp.utils.LoggerUtils;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("slslslsl","hhshshshshshsh");
        LoggerUtils.i("TAG","oncreste");
        Logger.t("ksks").i("lslslsllslslslsls");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
