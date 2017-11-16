package com.xiaan.liangyuan.liangyuanapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiaan.liangyuan.liangyuanapp.R;
import com.xiaan.liangyuan.liangyuanapp.LoggerUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoggerUtils.i(TAG, stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
