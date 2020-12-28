package com.chapter5;

import android.os.Bundle;
import android.webkit.WebViewFragment;

public class Chapter5_2WebViewFragment extends WebViewFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		getWebView().loadUrl("http://www.google.com");
	}
}
