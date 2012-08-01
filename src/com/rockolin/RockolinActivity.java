package com.rockolin;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rockolin.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RockolinActivity extends Activity {
	public static final String PREFS_NAME = "LoginPrefs";
	public final static String EXTRA_MESSAGE = "com.rockolin.USERNAME";
	public final static String EXTRA_PASS = "com.rockolin.PASSWORD";
	Activity Tview = this;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Parse.initialize(this, "APPDEGElR1SjxOeqRTb7dtQanRm38U4G6Udhbboe", "SM5CW4hkadXM4pi3RipbDCMZNpEnyID76hFRvwFV");
        
        /*SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		if (settings.getString("logged", "").toString().equals("logged")) {
			Intent intent = new Intent(Tview, DashboardActivity.class);
			startActivity(intent);
		}*/
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
        	startActivity(new Intent(Tview, DashboardActivity.class));
			finish();
        }
    }
    
    public void login(View view){
    	String username = ((EditText) findViewById(R.id.username)).getText().toString();
    	String password = ((EditText) findViewById(R.id.password)).getText().toString();
    	if(!(username.isEmpty())&&!(password.isEmpty())){
	    	ParseUser.logInInBackground(username, password, new LogInCallback() {
	    		  public void done(ParseUser user, ParseException e) {
	    		    if (user != null) {
	    		    	Toast.makeText(Tview, "Lets Party", Toast.LENGTH_SHORT).show();
	    		    	//setUser();
	    		    	Intent inte = new Intent(Tview, HomeActivity.class);
						startActivity(inte);
						finish();
	    		    } else {
	    		    	Toast.makeText(Tview, "Signin Error, please try again later.", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
	    		    }
	    		  }
	    	});
    	}
	}
    
    private void setUser(){
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("logged", "logged");
		editor.putString("username", ((EditText) findViewById(R.id.username)).getText().toString());
		editor.commit();
    }
    
    public void register(View view){
    	
		EditText usernameF = (EditText) findViewById(R.id.username);
		EditText passwordF = (EditText) findViewById(R.id.password);
		
		String username = usernameF.getText().toString();
		String password = passwordF.getText().toString();
		
		if(!(username.isEmpty())&&!(password.isEmpty())){
			Intent intent = new Intent(this, RegisterActivity.class);
			
			intent.putExtra(EXTRA_MESSAGE, username);
			intent.putExtra(EXTRA_PASS, password);
			
			startActivity(intent);
		}else{
			Toast.makeText(this, "Set Username & Password :)", Toast.LENGTH_SHORT).show();
		}
    }
}