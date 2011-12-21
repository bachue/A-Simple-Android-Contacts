package com.gitcafe.bachue;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class ContactsActivity extends TabActivity {
    private TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tabHost = getTabHost();
        
        tabHost.addTab(tabHost.newTabSpec("Call History")
        			          .setIndicator("Call History")
        			          .setContent(new Intent(this, CallHistoryTabActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("People")
        					  .setIndicator("People")
        					  .setContent(new Intent(this, PeopleTabActivity.class)));
        tabHost.setCurrentTab(0);
   }
}