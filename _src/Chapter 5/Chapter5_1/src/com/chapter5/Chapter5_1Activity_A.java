package com.chapter5;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chapter5.Chapter5_1FragmentA.OnBookSelectedListener;

public class Chapter5_1Activity_A extends Activity implements OnBookSelectedListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
	}

	public void addFragment() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		Chapter5_1FragmentA fragment = new Chapter5_1FragmentA();
		fragmentTransaction.add(R.id.layout_small_a, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onBookSelected(int bookIndex) {
		
		FragmentManager fragmentManager = getFragmentManager();
		Fragment fragment_b = fragmentManager.findFragmentById(R.id.fragment_b);
		String writer = Book.WRITER_NAMES[bookIndex];
		if(fragment_b == null)
		{
			Intent intent = new Intent(this,
					Chapter5_1Activity_B.class);
			intent.putExtra("writer", writer);
			startActivity(intent);
		}
		else
		{
			TextView textViewWriter = (TextView)fragment_b.getView().findViewById(R.id.textViewWriter);
			textViewWriter.setText(writer);
		}
	}
}