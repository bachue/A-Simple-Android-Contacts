package com.gitcafe.bachue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class AddContactActivity extends Activity {
	private ListView addcontactactivity_listview;
	private View phoneview;
	private View emailview;
	private Spinner phonetypespinner;
	private Spinner emailtypespinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);
        
        addcontactactivity_listview = (ListView) findViewById(R.id.addcontactlist);
        final AddContactActivityAdapter addContactActivityAdapter = new AddContactActivityAdapter(this);
        addcontactactivity_listview.setAdapter(addContactActivityAdapter);
        
        phoneview = addContactActivityAdapter.getView(
        		AddContactActivityAdapter.PHONE_NUMBER, null, null);
        emailview = addContactActivityAdapter.getView(
        		AddContactActivityAdapter.EMAIL, null, null);
        
        phonetypespinner = (Spinner) phoneview.findViewById(R.id.phonetypespinner);
        final ArrayAdapter<CharSequence> phonetype_adapter = 
        		ArrayAdapter.createFromResource(this, R.array.phone_types, android.R.layout.simple_spinner_item);
        phonetype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phonetypespinner.setAdapter(phonetype_adapter);
        
        emailtypespinner = (Spinner) emailview.findViewById(R.id.emailtypespinner);
        final ArrayAdapter<CharSequence> emailtype_adapter = 
        		ArrayAdapter.createFromResource(this, R.array.email_types, android.R.layout.simple_spinner_item);
        emailtype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emailtypespinner.setAdapter(emailtype_adapter);
    }
}
