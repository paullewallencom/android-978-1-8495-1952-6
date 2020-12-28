package com.chapter3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.StreamItemPhotos;
import android.provider.ContactsContract.StreamItems;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Chapter3_1Activity extends Activity implements OnClickListener {

	Button insertButton;
	Button listButton;
	Button chooseButton;
	TextView txt1;
	TextView txt2;
	TextView txt3;
	long rawContactId;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		insertButton = (Button) this.findViewById(R.id.buttonInsert);
		insertButton.setOnClickListener(this);
		listButton = (Button) this.findViewById(R.id.buttonList);
		listButton.setOnClickListener(this);
		txt1 = (TextView) this.findViewById(R.id.txt1);
		txt2 = (TextView) this.findViewById(R.id.txt2);
		txt3 = (TextView) this.findViewById(R.id.txt3);

	}

	public void getStreams(long rawContactId) {
		long contactId = getContactId(rawContactId);
		ContentResolver cr = getContentResolver();
		Cursor pCur = cr.query(ContactsContract.StreamItems.CONTENT_URI, null,
				ContactsContract.StreamItems.CONTACT_ID + " = ?",
				new String[] { String.valueOf(contactId) }, null);
		int i = 0;
		if (pCur.getCount() > 0) {
			while (pCur.moveToNext()) {
				String text = pCur.getString(pCur
						.getColumnIndex(ContactsContract.StreamItems.TEXT));
				if (i == 0)
					this.txt1.setText(text);
				else if (i == 1)
					this.txt2.setText(text);
				else if (i == 2)
					this.txt3.setText(text);
				i++;

			}
		}
		pCur.close();

	}


	public Uri addRawContact(String accountName, String accountType) {
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(RawContacts.ACCOUNT_TYPE, accountType);
		values.put(RawContacts.ACCOUNT_NAME, accountName);
		Uri rawContactUri = cr.insert(RawContacts.CONTENT_URI, values);
		return rawContactUri;
	}

	public long addContact(String name, String phone, String email,
			String accountName, String accountType) {

		Uri rawContactUri = addRawContact(accountName, accountType);

		if (rawContactUri != null) {
			long rawContactId = ContentUris.parseId(rawContactUri);

			addName(name, rawContactId);

			addPhoneNumber(phone, rawContactId);

			addEmail(email, rawContactId);

			addContactStreamItem(rawContactId, accountName, accountType, "Social Media Update 1");
			addContactStreamItem(rawContactId, accountName, accountType, "Social Media Update 2");
			addContactStreamItem(rawContactId, accountName, accountType, "Social Media Update 3");

			return rawContactId;
		}

		return 0;
	}

	private void addEmail(String email, long rawContactId) {
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Email.ADDRESS, email);
		values.put(Email.TYPE, Email.TYPE_OTHER);
		values.put(Email.MIMETYPE, Email.CONTENT_ITEM_TYPE);
		values.put(Data.RAW_CONTACT_ID, rawContactId);
		cr.insert(Data.CONTENT_URI, values);
	}

	private void addPhoneNumber(String phone, long rawContactId) {

		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Phone.NUMBER, phone);
		values.put(Phone.TYPE, Phone.TYPE_OTHER);
		values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		values.put(Data.RAW_CONTACT_ID, rawContactId);
		cr.insert(Data.CONTENT_URI, values);
	}

	private void addName(String name, long rawContactId) {
		ContentValues values = new ContentValues();
		values.put(Data.RAW_CONTACT_ID, rawContactId);
		values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		values.put(StructuredName.DISPLAY_NAME, name);
		getContentResolver().insert(Data.CONTENT_URI, values);
	}

	private long addContactStreamItem(long rawContactId, String accountName,
			String accountType, String text) {

		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(StreamItems.RAW_CONTACT_ID, rawContactId);
		values.put(StreamItems.TEXT, text);
		values.put(StreamItems.TIMESTAMP, Calendar.getInstance().getTime()
				.getTime());
		Uri.Builder builder = StreamItems.CONTENT_URI.buildUpon();
		builder.appendQueryParameter(RawContacts.ACCOUNT_NAME, accountName);
		builder.appendQueryParameter(RawContacts.ACCOUNT_TYPE, accountType);
		Uri streamItemUri = cr.insert(builder.build(), values);
		long streamItemId = ContentUris.parseId(streamItemUri);
		
		addContactStreamPhoto(streamItemId, accountName, accountType);

		return streamItemId;
	}

	private long addContactStreamPhoto(long streamItemId,String accountName,
			String accountType) {
		ContentValues values = new ContentValues();
		values.put(StreamItemPhotos.STREAM_ITEM_ID, streamItemId);
		values.put(StreamItemPhotos.SORT_INDEX, 1);
		values.put(StreamItemPhotos.PHOTO, loadPhotoFromResource(R.drawable.ic_launcher));
		Uri.Builder builder = StreamItems.CONTENT_PHOTO_URI.buildUpon();
		builder.appendQueryParameter(RawContacts.ACCOUNT_NAME, accountName);
		builder.appendQueryParameter(RawContacts.ACCOUNT_TYPE, accountType);
		Uri photoUri = getContentResolver().insert(builder.build(), values);
		long photoId = ContentUris.parseId(photoUri);
		return photoId;
	}

	public long getContactId(long rawContactId) {
		Cursor cur = null;
		try {
			cur = this.getContentResolver().query(
					ContactsContract.RawContacts.CONTENT_URI,
					new String[] { ContactsContract.RawContacts.CONTACT_ID },
					ContactsContract.RawContacts._ID + "=" + rawContactId,
					null, null);
			if (cur.moveToFirst()) {
				return cur
						.getLong(cur
								.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cur != null) {
				cur.close();
			}
		}
		return -1l;
	}


	@Override
	public void onClick(View v) {
		if (v == insertButton)
			this.rawContactId = addContact("Murat Aydýn", "9999999",
					"maydin@gmail.com", "Murat", "com.google");
		else if (v == listButton) {
			getStreams(this.rawContactId);
		} 
	}
	
	private byte[] loadPhotoFromResource(int resourceId) {
		
        InputStream is = getResources().openRawResource(resourceId);
        return readInputStream(is);
    }
	private byte[] readInputStream(InputStream is) {
        try {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}