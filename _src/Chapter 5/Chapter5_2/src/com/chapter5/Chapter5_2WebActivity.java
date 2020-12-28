package com.chapter5;

import android.app.Activity;
import android.os.Bundle;

public class Chapter5_2WebActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction()
        .replace(android.R.id.content, new Chapter5_2WebViewFragment())
        .commit();
	}
}
