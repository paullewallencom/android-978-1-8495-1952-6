package com.chapter1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Chapter1Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {

		case R.id.settings:
			Toast.makeText(this, "Settings options menu button is pressed", Toast.LENGTH_LONG);
			return true;
		case R.id.about:
			Toast.makeText(this, "About options menu button is pressed", Toast.LENGTH_LONG);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}