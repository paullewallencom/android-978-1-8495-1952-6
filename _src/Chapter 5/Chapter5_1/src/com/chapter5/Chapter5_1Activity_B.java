package com.chapter5;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Chapter5_1Activity_B extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        
        Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String s = extras.getString("writer");
			TextView view = (TextView) findViewById(R.id.textViewWriter);
			view.setText(s);
		}
    }
}
