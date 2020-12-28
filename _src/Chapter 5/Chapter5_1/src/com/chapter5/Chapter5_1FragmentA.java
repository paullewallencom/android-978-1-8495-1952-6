package com.chapter5;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Chapter5_1FragmentA extends ListFragment implements
		OnItemClickListener {

	OnBookSelectedListener mListener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, Book.BOOK_NAMES);
		this.setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// String writer = Book.WRITER_NAMES[position];
		// Intent intent = new Intent(getActivity().getApplicationContext(),
		// Chapter5_1ActivitySmall_B.class);
		// intent.putExtra("writer", writer);
		// startActivity(intent);
		mListener.onBookSelected(position);
	}

	public interface OnBookSelectedListener {
		public void onBookSelected(int bookIndex);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mListener = (OnBookSelectedListener) activity;
	}

}
