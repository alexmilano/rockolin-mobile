package com.rockolin;

import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CreateActivity extends Activity {
	ListView musicList;
	//Cursor musicCursor;
	MediaPlayer mMediaP;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);

		//ESTO VA EN LA PARTY
		//IntentIntegrator intInt = new IntentIntegrator(this);
		//intInt.shareText("hello");
		
	}
	
	public void createParty(View view){
		String name = ((EditText)findViewById(R.id.party_name)).toString();
		String address = ((EditText)findViewById(R.id.party_address)).toString();
		
		if((!name.isEmpty()) && (!address.isEmpty())){
			ParseUser user = ParseUser.getCurrentUser();
			ParseObject party = new ParseObject("party");
			party.put("user", user);
			party.put("name", name);
			party.put("address", address);
			party.saveInBackground();
			Toast.makeText(this, "Party created successfully!", Toast.LENGTH_LONG);
			
			startActivity(new Intent(this, DashboardActivity.class));
			finish();
		}else{
			Toast.makeText(this, "We need some data for the Party!", Toast.LENGTH_SHORT);
		}
	}
	
}
