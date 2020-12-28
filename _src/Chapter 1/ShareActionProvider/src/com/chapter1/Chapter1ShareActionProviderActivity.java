package com.chapter1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class Chapter1ShareActionProviderActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	ShareActionProvider myShareActionProvider;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		MenuItem item = menu.findItem(R.id.share);
		myShareActionProvider = (ShareActionProvider)item.getActionProvider();
		myShareActionProvider.setShareHistoryFileName(
		ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
		myShareActionProvider.setShareIntent(getShareIntent());
		
		return true;
    }
    
    private Intent getShareIntent() {
    	Intent shareIntent = new Intent(Intent.ACTION_SEND);
    	shareIntent.setType("text/plain");
    	shareIntent.putExtra(Intent.EXTRA_TEXT, 
    	"www.somesite.com");
    	return shareIntent;
    	}

}