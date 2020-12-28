package com.chapter5;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Chapter5_2Fragment extends Fragment implements OnClickListener{

	Button fragmentButton;
	Button openWebButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment, container, false);
		fragmentButton = (Button)view.findViewById(R.id.buttonFragment);	
		fragmentButton.setOnClickListener(this);
		
		openWebButton = (Button)view.findViewById(R.id.buttonOpenWeb);
		openWebButton.setOnClickListener(this);

		setHasOptionsMenu(true);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(v == fragmentButton)
		{
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			Chapter5_2DialogFragment dialogFragment = new Chapter5_2DialogFragment();
			dialogFragment.show(transaction, "dialog_fragment");
		}
		else if( v == openWebButton)
		{
			Intent intent = new Intent(getActivity(),Chapter5_2WebActivity.class);
			startActivity(intent);
		}


	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_menu, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent = new Intent(getActivity(),Chapter5_2PreferenceActivity.class);
		startActivity(intent);
		return true;
	}
}
