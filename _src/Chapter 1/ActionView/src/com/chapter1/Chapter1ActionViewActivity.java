package com.chapter1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Chapter1ActionViewActivity extends Activity implements OnClickListener {
	Button buttonLarge;
	Button buttonMedium;
	Button buttonSmall;
	Menu menu;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	this.menu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		MenuItem item = menu.findItem(R.id.size);
		item.setOnActionExpandListener(new Chapter1ActionListener(this));
		buttonLarge = (Button)item.getActionView().findViewById(R.id.buttonLarge);
		buttonLarge.setOnClickListener(this);
		
		buttonMedium = (Button)item.getActionView().findViewById(R.id.buttonMedium);
		buttonMedium.setOnClickListener(this);
		
		buttonSmall = (Button)item.getActionView().findViewById(R.id.buttonSmall);
		buttonSmall.setOnClickListener(this);
		
		return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {

		case R.id.size:
			Toast.makeText(this, "Size options menu button is pressed", Toast.LENGTH_LONG).show();
			return true;
		case R.id.about:
			Toast.makeText(this, "About options menu button is pressed", Toast.LENGTH_LONG).show();
			return true;
		case R.id.settings:
			Toast.makeText(this, "Settings options menu button is pressed", Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		
		if(v == buttonLarge )
		{
			Toast.makeText(this, "Large button is pressed", Toast.LENGTH_LONG).show();
			menu.findItem(R.id.size).collapseActionView();
		}
		else if(v == buttonMedium )
		{
			Toast.makeText(this, "Medium button is pressed", Toast.LENGTH_LONG).show();
			menu.findItem(R.id.size).collapseActionView();
		}
		else if(v == buttonSmall)
		{
			Toast.makeText(this, "Small button is pressed", Toast.LENGTH_LONG).show();
			menu.findItem(R.id.size).collapseActionView();
		}
		
	}
	
	public static class Chapter1ActionListener implements OnActionExpandListener
	{
		Activity activity;
		
		public Chapter1ActionListener(Activity activity)
		{
			this.activity = activity;
		}

		@Override
		public boolean onMenuItemActionCollapse(MenuItem item) {

			Toast.makeText(activity, item.getTitle()+" button is collapsed", Toast.LENGTH_LONG).show();
			return true;
		}

		@Override
		public boolean onMenuItemActionExpand(MenuItem item) {
			Toast.makeText(activity, item.getTitle()+" button is expanded", Toast.LENGTH_LONG).show();
			return true;
		}
		
	}
}