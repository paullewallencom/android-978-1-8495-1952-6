package com.chapter4;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Attendees;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Chapter4_1Activity extends Activity implements OnClickListener {
	
	Button insertEventButton;
	Button insertAttendeeButton;
	Button insertReminder;
	long calendarID;
	long eventID;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        insertEventButton = (Button)this.findViewById(R.id.buttonInsertEvent);
        insertEventButton.setOnClickListener(this);
        
        insertAttendeeButton = (Button)this.findViewById(R.id.buttonInsertAttendee);
        insertAttendeeButton.setOnClickListener(this);
        
        insertReminder = (Button)this.findViewById(R.id.buttonInsertReminder);
        insertReminder.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if(v == insertEventButton)
			addEvent();
		else if(v == insertAttendeeButton)
			addAttendee();
		else if(v == insertReminder)
			addReminder();
	}

	private void addEvent() {
		calendarID = getCalendarID();
		ContentValues eventValues = new ContentValues ();

		eventValues.put (CalendarContract.Events.CALENDAR_ID,calendarID);
		eventValues.put (CalendarContract.Events.TITLE,"Event 1");
		eventValues.put (CalendarContract.Events.DESCRIPTION,
		    "Testing Calendar API");
		eventValues.put (CalendarContract.Events.DTSTART,Calendar.getInstance().getTimeInMillis());
		eventValues.put (CalendarContract.Events.DTEND,Calendar.getInstance().getTimeInMillis());
		eventValues.put(CalendarContract.Events.EVENT_TIMEZONE,TimeZone.getDefault().toString());
		           
		Uri eventUri = this.getContentResolver().insert (CalendarContract.Events.CONTENT_URI, eventValues);
		eventID = ContentUris.parseId(eventUri);
	}
	
	private void addEventUsingIntent() {
		calendarID = getCalendarID();
		Intent intent = new Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis())
        .putExtra(CalendarContract.Events.DTEND, Calendar.getInstance().getTimeInMillis())
        .putExtra(CalendarContract.Events.TITLE,"Event 1")
        .putExtra(CalendarContract.Events.DESCRIPTION,"Testing Calendar API");
		startActivity(intent);     
	}
	private void addAttendee()
	{
		
		ContentValues cv = new ContentValues();
		cv.put(Attendees.ATTENDEE_NAME, "Murat AYDIN");
		cv.put(Attendees.ATTENDEE_EMAIL, "m_aydina@hotmail.com");
		cv.put(Attendees.EVENT_ID, eventID);
		cv.put(Attendees.ATTENDEE_RELATIONSHIP, Attendees.RELATIONSHIP_ATTENDEE);
		cv.put(Attendees.ATTENDEE_STATUS, Attendees.ATTENDEE_STATUS_INVITED);
		cv.put(Attendees.ATTENDEE_TYPE,Attendees.TYPE_OPTIONAL);
		
		
		this.getContentResolver().insert(CalendarContract.Attendees.CONTENT_URI, cv);
	}
	
	private void addReminder()
	{

		ContentValues values = new ContentValues();
		values.put(Reminders.MINUTES, 15);
		values.put(Reminders.EVENT_ID, eventID);
		values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
		this.getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);
	}
	public long getCalendarID() {
		Cursor cur = null;
		try {
			cur = this.getContentResolver().query(
					CalendarContract.Calendars.CONTENT_URI,
					null,null,null, null);
			if (cur.moveToFirst()) {
				return cur
						.getLong(cur
								.getColumnIndex(CalendarContract.Calendars._ID));
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
}