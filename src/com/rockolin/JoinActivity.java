package com.rockolin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class JoinActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);
		
	}
	
	public void scanParty(View view){
		IntentIntegrator intInt = new IntentIntegrator(this);
		intInt.initiateScan();
		//Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		//intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		//startActivityForResult(intent, 0);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	  if (scanResult != null) {
	    // handle scan result
		  Toast.makeText(this, scanResult.getContents(), Toast.LENGTH_SHORT).show();
	  }else{
		  Toast.makeText(this, "NADA", Toast.LENGTH_SHORT).show();
		  
	  }
	  // else continue with any other code you need in the method
	}
}
