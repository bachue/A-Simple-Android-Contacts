package com.gitcafe.bachue;

import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AddContactActivityAdapter extends BaseAdapter {
	private static int COUNT = 3;
	static final int NAME = 0, PHONE_NUMBER = 1, EMAIL = 2;
	private HashMap<Integer, View> views_hash;
	
	public AddContactActivityAdapter(final Context context) {
		views_hash = new HashMap<Integer, View>();
		final LayoutInflater mInflater = LayoutInflater.from(context);
		views_hash.put(NAME, mInflater.inflate(R.layout.addcontact_name, null));
		views_hash.put(PHONE_NUMBER, mInflater.inflate(R.layout.addcontact_phone, null));
		views_hash.put(EMAIL, mInflater.inflate(R.layout.addcontact_email, null));
	}
	
	public int getCount() {
		return COUNT;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		switch(position)
		{
		case NAME:
		case PHONE_NUMBER:
		case EMAIL:
			view = views_hash.get(position);
			break;
		default:
		}
		return view;
	}

}
