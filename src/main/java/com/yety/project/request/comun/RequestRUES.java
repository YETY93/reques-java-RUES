package com.yety.project.request.comun;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestRUES {
	
	
	public  void obtenerCokie(String urlCookie) throws Exception{
		//Map<String, String> cookiesObetnidas = new HashMap<String, String>();
		
		URL  url = new URL(urlCookie);
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		Map<String, List<String>> cookies = conexion.getHeaderFields();
		
		for (String nombreCookie : cookies.keySet()) {
			 System.out.println(nombreCookie + ": " + cookies.get(nombreCookie));
		}
		
	}

	
	public void obtenerCookieCM(String urlConsultar) throws Exception{
		CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);
		URL url = new  URL(urlConsultar);
		URLConnection connection = url.openConnection();
		connection.getContent();
		
		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		
		for (HttpCookie cookie : cookies) {
			System.out.println(cookie.getDomain());
			System.out.println(cookie);
		}
	}
	
	
}
