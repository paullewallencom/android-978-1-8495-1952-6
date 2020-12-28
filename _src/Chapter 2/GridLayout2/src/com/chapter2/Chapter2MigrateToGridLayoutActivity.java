package com.chapter2;

import android.app.Activity;
import android.os.Bundle;

public class Chapter2MigrateToGridLayoutActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_with_linearlayout);
        
        //setContentView(R.layout.main_with_gridlayout);
    }
}