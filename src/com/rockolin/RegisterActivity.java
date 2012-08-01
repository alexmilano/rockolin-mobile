package com.rockolin;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	public static final String PREFS_NAME = "LoginPrefs";
	private String username, password;
	Activity Tview = this;
	public final String THIS_USERNAME = "com.rockolin.USERNAME";
	ProgressDialog doin;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		Intent intent = getIntent();
		username = intent.getStringExtra(RockolinActivity.EXTRA_MESSAGE);
		password = intent.getStringExtra(RockolinActivity.EXTRA_PASS);
		((EditText)findViewById(R.id.username_register)).setText(username);
		((EditText)findViewById(R.id.password_register)).setText(password);
	}
	
	public void register(View view){
		doin = new ProgressDialog(this);
		doin.setMessage("Loading...");
		doin.setCancelable(false);
		doin.show();
		
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(((EditText)findViewById(R.id.email)).getText().toString());
		
		user.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub
				doin.dismiss();
				if(e == null){
					Toast.makeText(Tview, "Lets Party", Toast.LENGTH_SHORT);
					//setUser();
					Intent inte = new Intent(Tview, HomeActivity.class);
					startActivity(inte);
					finish();
				}else{
					Toast.makeText(Tview, "Signup Error, please try again later.", Toast.LENGTH_LONG);
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setUser(){
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("logged", "logged");
		editor.putString("username", ((EditText) findViewById(R.id.username)).getText().toString());
		editor.commit();
    }
}
