package com.gitcafe.bachue;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class PeopleTabActivity extends Activity {
	private ListView peoplelist;
	private Cursor contactscursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.people);
	    
	    peoplelist = (ListView) findViewById(R.id.peoplelist);
	    registerForContextMenu(peoplelist);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		queryPeople();
	}
	
	protected void queryPeople()
	{
		contactscursor = getContentResolver().query(
			Contacts.CONTENT_URI,
    		new String[] { Contacts._ID, Contacts.DISPLAY_NAME }, 
    		null, null, Contacts.DISPLAY_NAME);
		startManagingCursor(contactscursor);
		
		final SimpleCursorAdapter contactsAdapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_1, contactscursor, 
				new String[] { Contacts.DISPLAY_NAME },
				new int[] { android.R.id.text1 });
		peoplelist.setAdapter(contactsAdapter);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle(R.string.menu);
		getMenuInflater().inflate(R.menu.contextmenu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.show:
			contactscursor.moveToPosition(info.position);
			final String name = contactscursor.getString(contactscursor.getColumnIndex(Contacts.DISPLAY_NAME));
			Toast.makeText(this, "The person you selected is " + name, Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.optionmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.addnewcontact:
			final Intent intent = new Intent(this, AddContactActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
