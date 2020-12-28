package com.chapter1;

import android.app.Activity;
import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Chapter1ActionProvider extends ActionProvider implements OnMenuItemClickListener {

	Context mContext;
	
	public Chapter1ActionProvider(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateActionView() {
		
		 LayoutInflater layoutInflater = LayoutInflater.from(mContext);
         View view = layoutInflater.inflate(R.layout.action_provider, null);
         ImageButton button = (ImageButton) view.findViewById(R.id.button);

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(mContext, "Action Provider click", Toast.LENGTH_LONG).show();
             }
         });
         return view;
	}
	
	@Override
	public boolean onPerformDefaultAction() {
		
		Toast.makeText(mContext, "Action Provider click", Toast.LENGTH_LONG).show();
		return true;
	}
	
	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		
//		subMenu.clear();
//		subMenu.add("SubItem1").setOnMenuItemClickListener(this);
//		subMenu.add("SubItem2").setOnMenuItemClickListener(this);
		
		MenuInflater inflater = ((Activity)mContext).getMenuInflater();
		inflater.inflate(R.menu.menu2, subMenu);
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		
		Toast.makeText(mContext, "Sub Item click", Toast.LENGTH_LONG).show();
		return true;
	}
	
	@Override
	public boolean hasSubMenu() {
		return true;
	}

}
