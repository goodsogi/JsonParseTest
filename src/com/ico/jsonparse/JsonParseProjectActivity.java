package com.ico.jsonparse;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JsonParseProjectActivity extends Activity implements JsonParseManager.ICallBackFunction {
    /** Called when the activity is first created. */
	
	TextView textJsonOne;
	TextView textJsonTwo;
	TextView textJsonThree;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        JsonParseManager._init();
        uiInit();
        
    }
    
    private void uiInit(){
    	textJsonOne = (TextView)findViewById(R.id.text_json_one);
    	textJsonTwo = (TextView)findViewById(R.id.text_json_two);
    	textJsonThree = (TextView)findViewById(R.id.text_json_three);
    	
    	
    	Button btnJsonOne = (Button)findViewById(R.id.btn_json_one);
    	Button btnJsonTwo = (Button)findViewById(R.id.btn_json_two);
    	Button btnJsonThree = (Button)findViewById(R.id.btn_json_three);
    	
    	btnJsonOne.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JsonParseManager._call_json_one(JsonParseProjectActivity.this);
			}
		});
    	
    	btnJsonTwo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JsonParseManager._call_json_two(JsonParseProjectActivity.this);
			}
		});
    	
    	btnJsonThree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JsonParseManager._call_json_three(JsonParseProjectActivity.this);
			}
		});
    }

	@Override
	public void onJsonOneData(String resultData) {
		// TODO Auto-generated method stub
		Log.d("", "JsonParseProjectActivity - onJsonOneData - resultData : " + resultData);
		textJsonOne.setText(resultData);
		Toast.makeText(JsonParseProjectActivity.this, "onJsonOneData callback success", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onJsonTwoData(String resultData) {
		// TODO Auto-generated method stub
		Toast.makeText(JsonParseProjectActivity.this, "onJsonTwoData callback success", Toast.LENGTH_SHORT).show();
		textJsonTwo.setText(resultData);
	}

	@Override
	public void onJsonThreeData(String resultData) {
		// TODO Auto-generated method stub
		Toast.makeText(JsonParseProjectActivity.this, "onJsonThreeData callback success", Toast.LENGTH_SHORT).show();
		textJsonThree.setText(resultData);
	}
}