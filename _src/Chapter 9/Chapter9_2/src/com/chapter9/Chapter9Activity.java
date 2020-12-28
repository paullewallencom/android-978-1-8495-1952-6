package com.chapter9;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Chapter9Activity extends Activity implements
		CreateNdefMessageCallback {

	NfcAdapter mNfcAdapter;
	TextView mInfoText;
	ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) findViewById(R.id.imageView);
		mInfoText = (TextView) findViewById(R.id.textView);
		// Check for available NFC Adapter

		mNfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());

		if (mNfcAdapter == null) {
			mInfoText = (TextView) findViewById(R.id.textView);
			mInfoText.setText("NFC is not available on this device.");
			finish();
			return;
		}
		// Register callback to set NDEF message
		mNfcAdapter.setNdefPushMessageCallback(this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {

		Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.ic_launcher);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();

		NdefMessage msg = new NdefMessage(new NdefRecord[] {
				createMimeRecord("application/com.chapter9", byteArray)
				, NdefRecord.createApplicationRecord("com.chapter9") });
		return msg;
	}

	@Override
	public void onResume() {
		super.onResume();
		// Check to see that the Activity started due to an Android Beam
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			processIntent(getIntent());
		}
	}

	@Override
	public void onNewIntent(Intent intent) {
		// onResume gets called after this to handle the intent
		setIntent(intent);
	}


	void processIntent(Intent intent) {

		Parcelable[] rawMsgs = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		// only one message sent during the beam
		NdefMessage msg = (NdefMessage) rawMsgs[0];
		// record 0 contains the MIME type, record 1 is the AAR
		byte[] bytes = msg.getRecords()[0].getPayload();
		Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

		imageView.setImageBitmap(bmp);
	}

	public NdefRecord createMimeRecord(String mimeType, byte[] payload) {

		byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				mimeBytes, new byte[0], payload);
		return mimeRecord;
	}
}
