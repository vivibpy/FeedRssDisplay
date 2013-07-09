package com.example.feedrssdisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_ADR="com.example.FeedRSSDisplay.ADR";
	public final static String EXTRA_TAG1="com.example.FeedRSSDisplay.TAG1";
	public final static String EXTRA_TAG2= "com.example.FeedRSSDisplay.TAG2";
	public final static String EXTRA_TAG3= "com.example.FeedRSSDisplay.TAG3";
	public final static String EXTRA_TAG4= "com.example.FeedRSSDisplay.TAG4";
	public final static String EXTRA_CHECKBOX= "com.example.FeedRSSDisplay.CHECKBOX";
	
	private int[] checkbox;
	private boolean checkimg1=false;
	private boolean checkimg2=false;
	private boolean checkimg3=false;
	private boolean checkimg4=false;
	
	
	
	
	public boolean getcheck(int a){
		switch(a){
		case 1:
			return checkimg1;
		case 2:
			return checkimg2;
		case 3:
			return checkimg3;
		case 4:
			return checkimg4;
		default:
			return false;
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void checkboxes(){
		checkbox=null;
		
		if(checkimg1){
			checkbox[0]=1;
		}else{
			checkbox[0]=0;
		}
		if(checkimg2){
			checkbox[1]=1;
		}else{
			checkbox[1]=0;
		}
		if(checkimg3){
			checkbox[2]=1;
		}else{
			checkbox[2]=0;
		}
		if(checkimg4){
			checkbox[3]=1;
		}else{
			checkbox[3]=0;
		}
	}
	
	
	public void CheckImage(View view){
		boolean checked = ((CheckBox) view).isChecked();
		
		if (checked){
			
			switch(view.getId()){
			
			case R.id.box_img_1:
				this.checkimg1=true;
				Log.i("box1", "true");
			case R.id.box_img_2:
				this.checkimg2=true;
				Log.i("box2", "true");
			case R.id.box_img_3:
				this.checkimg3=true;
				Log.i("box3", "true");
			case R.id.box_img_4:
				this.checkimg4=true;
				Log.i("box4", "true");
			
			}
			
		}
	}
	
	public void sendFeed(View view){
		Log.i("buttonClicked", "true");
		Intent intent1 = new Intent(this, DisplayRss.class);
		Intent intent = new Intent(this, ParserRSS.class);
		EditText eadr = (EditText) findViewById(R.id.edit_message_adr);
		EditText etag1 = (EditText) findViewById(R.id.edit_message_1);
		EditText etag2 = (EditText) findViewById(R.id.edit_message_2);
		EditText etag3 = (EditText) findViewById(R.id.edit_message_3);
		EditText etag4 = (EditText) findViewById(R.id.edit_message_4);
		
		String adr = eadr.getText().toString();
		String tag1 = etag1.getText().toString();
		String tag2 = etag2.getText().toString();
		String tag3 = etag3.getText().toString();
		String tag4 = etag4.getText().toString();
		
		
		intent1.putExtra(EXTRA_ADR, adr);
		intent.putExtra(EXTRA_TAG1, tag1);
		intent.putExtra(EXTRA_TAG2, tag2);
		intent.putExtra(EXTRA_TAG3, tag3);
		intent.putExtra(EXTRA_TAG4, tag4);
		intent.putExtra(EXTRA_CHECKBOX, checkbox);
		
		Log.i("etag1", tag1);
		
		startActivity(intent1);
		
		
	}
	

}
