package com.bobo.searchphone;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.bobo.util.HttpUtil;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	String url = "http://192.168.101.154:8080/wochacha";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView phoneNumber = (TextView)findViewById(R.id.phoneNumber);
		final LinearLayout belongPanel = (LinearLayout)findViewById(R.id.belongPanel);
		final TextView belongPhoneText = (TextView)findViewById(R.id.belongPhoneText);
		Button searchPhoneBtn = (Button)findViewById(R.id.searchPhoneBtn);
		searchPhoneBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				belongPanel.setVisibility(View.VISIBLE);
				
				try{
					JSONObject json = MainActivity.this.searchPhoneBelong(phoneNumber.getText().toString());
					belongPhoneText.setText(json == null ? "无查询结果" : json.getString("mobileArea"));
				}catch(Exception e){
					Log.e("AppErrorLog", e.getMessage());
				}
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * 查询手机归属地
	 * @param 查询地址
	 */
	private JSONObject searchPhoneBelong(String mobileNum) throws Exception {
		Map<String , String> params = new HashMap<String , String>();
		params.put("mobileNum", mobileNum);
		String address = "/mobileNumber/findMobile";
		JSONObject json = HttpUtil.postRequest(url.concat(address) , params);
		return json;
	}

}
