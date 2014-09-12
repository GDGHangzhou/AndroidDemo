package com.bobo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class HttpUtil {

	
	
	/**
	 * postRequest����
	 * @param url
	 */
	public static JSONObject postRequest(String url , Map<String , String> map){
		JSONObject json = null;
		// ��һ��������HttpPost����
		HttpPost httpPost = new HttpPost(url);
		// ����HTTP POST�������������NameValuePair����
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		for(String key : map.keySet()){
			params.add(new BasicNameValuePair(key , map.get(key)));
		}
		
		System.out.println("result1");
		// ����httpPost�������
		try
		{
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// �ڶ�����ʹ��execute��������HTTP POST���󣬲�����HttpResponse����
			HttpResponse httpResponse;
			try
			{
				httpResponse = new DefaultHttpClient().execute(httpPost);
				System.out.println("result");
				if (httpResponse.getStatusLine().getStatusCode() == 200)
				{
					// ��������ʹ��getEntity�����õ����ؽ��
					String result = EntityUtils.toString(httpResponse.getEntity());
					System.out.println("result" + result);
					json = new JSONObject(result);
				}
			}
			catch (ClientProtocolException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("ex", e.getMessage());
			}
			catch(Exception e)
			{
				
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
}
