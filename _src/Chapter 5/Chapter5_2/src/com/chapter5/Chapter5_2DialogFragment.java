package com.chapter5;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Chapter5_2DialogFragment extends DialogFragment implements OnClickListener{

	Button okButton;
	Button cancelButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.dialog_fragment, container, false);
		
		okButton = (Button)view.findViewById(R.id.buttonOk);
		okButton.setOnClickListener(this);
		
		cancelButton = (Button)view.findViewById(R.id.buttonCancel);
		cancelButton.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		
		if(v == cancelButton)
			dismiss();
		else if( v == okButton)
		{
			Toast.makeText(this.getActivity(), "Item is deleted.", Toast.LENGTH_LONG).show();
			dismiss();
		}
		
	}
}
