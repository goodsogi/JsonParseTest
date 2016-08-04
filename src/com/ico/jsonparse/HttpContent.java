package com.ico.jsonparse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

public class HttpContent 
{
	public HttpContent() throws Exception
	{
		//String str = getStringContent("http://ollehtvtest.paran.com/api/vod_list.jsp?menuid=26823");
		//String str = getStringContent("http://www.naver.com");
		//Log.d("this", " >>>" + jStr + "<<< ");
	}
	
	public static String getStringContent(String uri) throws Exception 
	{
	    try 
	    {
	    	HttpClient client = new DefaultHttpClient();
	        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
	        HttpGet request = new HttpGet();
	        request.setHeader("Content-Type", "text/plain; charset=utf-8");
	        request.setURI(new URI(uri));
	        HttpResponse response = client.execute(request);
	        InputStream is = response.getEntity().getContent();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"), 1024);
 
	        StringBuffer stringBuffer = new StringBuffer("");
	        String line = "";
 
//	        String NL = System.getProperty("line.separator");
	        while ((line = bufferedReader.readLine()) != null) 
	        {
//	        	line.trim().replaceAll("\\p{Space}", "");
	        	stringBuffer.append(line);
	            //System.out.print(stringBuffer);
	        }
	        bufferedReader.close();
	        String page = stringBuffer.toString();
	        //System.out.println(page+"page");
	        return page;
	    } 
	    finally 
	    {
	        // any cleanup code...
	    }
	}

	
	
	
}
