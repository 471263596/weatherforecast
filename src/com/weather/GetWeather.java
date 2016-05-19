package com.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class GetWeather {
	public static void main(String[] args) {
//		Client client;
//		try {
//			client = new Client(new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl"));
//			Object[] results = client.invoke("getSupportCity", new Object[] { "灞变笢" });
//			System.out.println(results[0]);
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//System.out.println(post());
		String[] result=post();
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

	}
	//http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName?theCityName=骞垮窞
	public static String[] post() {
		OutputStreamWriter out = null;
		String[] result=null;
		try {
//			URL urlTemp = new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getSupportCity");
			URL urlTemp = new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName");//需要连网
			URLConnection connection = urlTemp.openConnection();
			connection.setDoOutput(true);
			out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//			out.write("byProvinceName=山东");
			out.write("theCityName=济南");
			out.flush();
			String str=null;
			String content=null;
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			while ((str = l_reader.readLine()) != null) {
				content+=str;
			}
			int a=content.indexOf("<string>");
			int b=content.lastIndexOf("</string>");
			result=content.substring(a+8, b).split("</string>  <string>");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
