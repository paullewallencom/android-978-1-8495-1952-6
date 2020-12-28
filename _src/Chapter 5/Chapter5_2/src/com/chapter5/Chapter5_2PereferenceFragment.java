package com.chapter5;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Chapter5_2PereferenceFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
	}

}
