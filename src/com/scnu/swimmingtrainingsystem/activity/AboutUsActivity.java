package com.scnu.swimmingtrainingsystem.activity;

import android.app.Activity;
import android.os.Bundle;

import com.scnu.swimmingtrainingsystem.R;

/**
 * Created by lixinkun on 15/12/14.
 */
public class AboutUsActivity extends Activity {

    private MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        app = (MyApplication)getApplication();
        app.addActivity(this);
    }
}
