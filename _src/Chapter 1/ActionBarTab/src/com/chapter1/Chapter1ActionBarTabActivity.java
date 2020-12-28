package com.chapter1;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class Chapter1ActionBarTabActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tab = actionBar
				.newTab()
				.setText("First tab")
				.setTabListener(
						new Chapter1TabListener<FragmentA>(this, "fragmentA",
								FragmentA.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("Second Tab")
				.setTabListener(
						new Chapter1TabListener<FragmentB>(this, "fragmentB",
								FragmentB.class));
		actionBar.addTab(tab);
	}

	public static class Chapter1TabListener<T extends Fragment> implements
			TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		public Chapter1TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {

			if (mFragment == null) {
				
				mFragment = Fragment.instantiate(mActivity, mClass.getName());			
				ft.add(android.R.id.content, mFragment, mTag);
				
			} else {
				ft.attach(mFragment);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}
}