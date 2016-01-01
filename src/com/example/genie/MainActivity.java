package com.example.genie;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button button_createWish;
	Button button_myWishes;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		new Thread() {
//        	public void run() {
//        		getAllWishes();
//        	}
//        }.start();
		
		button_createWish = (Button) findViewById(R.id.button_createWish);
		button_createWish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent openMainActivity =  new Intent(MainActivity.this, CreateWishActivity.class);
	            startActivity(openMainActivity);
	            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
		
		button_myWishes = (Button) findViewById(R.id.button_myWishes);
		button_myWishes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent openMainActivity =  new Intent(MainActivity.this, MyWishesActivity.class);
	            startActivity(openMainActivity);
            }
        });
		
		LinearLayout linear_wishList = (LinearLayout)findViewById(R.id.linear_wishList); 
		
		for(int i=1; i<=10; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		            LinearLayout.LayoutParams.MATCH_PARENT,
		            LinearLayout.LayoutParams.WRAP_CONTENT);
		    Button btn = new Button(this);
		    btn.setId(i);
		    final int id_ = btn.getId();
		    btn.setText("wish " + id_ + "");
		    btn.setBackgroundColor(Color.rgb(128, 128, 128));
		    linear_wishList.addView(btn, params);

		    btn.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View view) {
		        	Intent openMainActivity =  new Intent(MainActivity.this, DetailWishActivity.class);
		        	openMainActivity.putExtra("detailWish", id_);
		            startActivity(openMainActivity);
		        }
		    });
		}
	}
	
	private void getAllWishes() {
		StringBuilder sb = new StringBuilder();
		
		try {
			URL url = new URL("https://ec2-52-24-78-6.us-west-2.compute.amazonaws.com/getallwish");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			final ArrayList<String> wishList = new ArrayList<String>();
			String line = null;
			while((line=br.readLine()) != null) {
				wishList.add(line);
				//sb.append(line);
			}
			
			urlConnection.disconnect();
			handler.post(new Runnable() {
    			public void run() {
    				LinearLayout linear_wishList = (LinearLayout)findViewById(R.id.linear_wishList);
    				
    				for(int i=1; i<=wishList.size(); i++) {
    					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    				            LinearLayout.LayoutParams.MATCH_PARENT,
    				            LinearLayout.LayoutParams.WRAP_CONTENT);
    					Button btn = new Button(getBaseContext());
    				    btn.setId(i);
    				    final int id_ = btn.getId();
    				    btn.setText("wish " + id_ + "");
    				    btn.setBackgroundColor(Color.rgb(70, 80, 90));
    				    linear_wishList.addView(btn, params);

    				    btn.setOnClickListener(new View.OnClickListener() {
    				        public void onClick(View view) {
    				        	Intent openMainActivity =  new Intent(MainActivity.this, DetailWishActivity.class);
    				        	openMainActivity.putExtra("detailWish", id_);
    				            startActivity(openMainActivity);
    				        }
    				    });
    				}
    			}
    		});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
