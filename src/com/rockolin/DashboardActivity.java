package com.rockolin;

import com.parse.ParseUser;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity {
	public static final String PREFS_NAME = "LoginPrefs";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        ParseUser currentUser = ParseUser.getCurrentUser();
        
        ((TextView) findViewById(R.id.username_text)).setText(currentUser.getUsername());
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater Inflater = getMenuInflater();
		Inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.logout) {
			/*SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.remove("logged");
			editor.commit();*/
			ParseUser.logOut();
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void createParty(View view){
		startActivity(new Intent(this, CreateActivity.class));
	}
	
	public void joinParty(View view){
		startActivity(new Intent(this, JoinActivity.class));
	}
}
