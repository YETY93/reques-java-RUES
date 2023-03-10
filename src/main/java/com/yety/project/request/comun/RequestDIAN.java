package com.yety.project.request.comun;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yety.project.request.comun.constants.IContsRequestDIAN;

public class RequestDIAN {
	/* formato de postman
	public void obtenerHtmlDIANrut() {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
				RequestBody body = RequestBody.create(mediaType, "vistaConsultaEstadoRUT:formConsultaEstadoRUT:numNit=900521211&vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.x=&vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.y=&com.sun.faces.VIEW=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&vistaConsultaEstadoRUT:formConsultaEstadoRUT=vistaConsultaEstadoRUT:formConsultaEstadoRUT");
				Request request = new Request.Builder()
				  .url("https://muisca.dian.gov.co/WebRutMuisca/DefConsultaEstadoRUT.faces")
				  .method("POST", body)
				  .addHeader("Content-Type", "application/x-www-form-urlencoded")
				  .build();
				Response response = client.newCall(request).execute();
	}
	*/
	
	public static String obtenerHtmlDIANrut(String numNit, String urldian) throws Exception {
		
		String separadorAnd = "&";
		String numNitCompletoParametro = "vistaConsultaEstadoRUT:formConsultaEstadoRUT:numNit" 
										+ "=" + numNit;
		String btnBuscarXParametro = "vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.x" 
										+ "=" + IContsRequestDIAN.PARAMETER_BTN_BUSCAR_X;
		String btnBuscarYParametro = "vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.y" 
										+ "=" + IContsRequestDIAN.PARAMETER_BTN_BUSCAR_Y;
		String facesViewParametro = "com.sun.faces.VIEW" 
										+ "=" + IContsRequestDIAN.COM_SUN_FACES_VIEW;
		String ConsultaEstadoRUTParametro  = "vistaConsultaEstadoRUT:formConsultaEstadoRUT"
										+ "=" + IContsRequestDIAN.FORM_COMSULTA_ESTADO_RUT;
		
		String parametrosCompletos = numNitCompletoParametro + separadorAnd + btnBuscarXParametro + separadorAnd
										+ btnBuscarYParametro + separadorAnd + facesViewParametro + separadorAnd 
										+ facesViewParametro + separadorAnd + ConsultaEstadoRUTParametro;
		
		byte[] postData = parametrosCompletos.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		URL url = new URL(urldian);
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		
		conexion.setInstanceFollowRedirects(false);
		conexion.setRequestMethod("POST");
		conexion.setRequestProperty("Content-Type", IContsRequestDIAN.CONTENT_TYPE_FORM_URLENCODE);
		//conexion.setRequestProperty("charset", "utf-8");
		conexion.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
		conexion.setDoOutput(true);
		conexion.setUseCaches(false);
		
		Reader in = new BufferedReader(new InputStreamReader(conexion.getInputStream(),"UTF-8" ));
		  
		for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
		
		System.out.println("####################################################");
		 StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        String response = sb.toString();
			
	        return response;
	}
}
