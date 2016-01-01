package com.example.genie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailWishActivity extends Activity {
	
	TextView text_wishTitle;
	TextView text_wishDetail;
	Button button_cheer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_wish);
		
		Intent intent = getIntent();
		
		text_wishTitle = (TextView) findViewById(R.id.text_wishTitle);
		text_wishTitle.setText("Wish #" + String.valueOf(intent.getIntExtra("detailWish", 0)));
		
		text_wishDetail = (TextView) findViewById(R.id.text_wishDetail);
		text_wishDetail.setText("Wish Detail");
		
		button_cheer = (Button) findViewById(R.id.button_cheer);
		button_cheer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getApplicationContext(), 
                        "Donate button is clicked", Toast.LENGTH_LONG).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_wish, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
