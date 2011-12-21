package com.gitcafe.bachue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.provider.CallLog.Calls;

public class CallHistoryTabActivity extends Activity {
	private Spinner viewspinner;
	private ListView callhistorylist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.callhistory);
	    
	    viewspinner = (Spinner) findViewById(R.id.viewspinner);
        final ArrayAdapter<CharSequence> view_adapter = 
        		ArrayAdapter.createFromResource(this, R.array.views, android.R.layout.simple_spinner_item);
        view_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewspinner.setAdapter(view_adapter);
        viewspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				queryCallHistory(position);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
        
        callhistorylist = (ListView) findViewById(R.id.callhistorylist);
	}
	
	protected void queryCallHistory(int condition)
	{
		String queryCondition = null;
		if(condition > 0)
		{
			queryCondition = Calls.TYPE + "=" + condition;
		}
        
        final Cursor cursor = getContentResolver().query(
        		Calls.CONTENT_URI, 
        		new String[] { Calls._ID, Calls.CACHED_NAME, Calls.NUMBER, Calls.DATE }, 
        		queryCondition, null, Calls.DATE + " desc");
        cursor.moveToFirst();
        
        final ArrayList<HashMap<String, String>> list_array = new ArrayList<HashMap<String,String>>();
        while(!cursor.isAfterLast())
        {
        	final HashMap<String, String> list_hash = new HashMap<String, String>();
        	final String name = cursor.getString(cursor.getColumnIndex(Calls.CACHED_NAME));
        	final String number = cursor.getString(cursor.getColumnIndex(Calls.NUMBER));
        	final String date = new Date(cursor.getLong(cursor.getColumnIndex(Calls.DATE))).toLocaleString();
        	list_hash.put(Calls.NUMBER, name != null ? name : number);
        	list_hash.put(Calls.DATE, date);
        	list_array.add(list_hash);
        	cursor.moveToNext();
        }
        final SimpleAdapter list_adapter = 
        		new SimpleAdapter(this, list_array, android.R.layout.simple_list_item_2, 
        				new String[] { Calls.NUMBER, Calls.DATE }, 
        				new int[] { android.R.id.text1, android.R.id.text2 });
        callhistorylist.setAdapter(list_adapter);
	}
	
	@Override
	protected void onResume() {
		super.onPause();
		queryCallHistory(viewspinner.getSelectedItemPosition());
	}
}
