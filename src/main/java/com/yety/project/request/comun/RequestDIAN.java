package com.yety.project.request.comun;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.yety.project.request.comun.constants.IContsRequestDIAN;

/**
 * @author Yesid Rangel
 *
 */

public class RequestDIAN {

	/**
	 * @param numNit
	 * @param urldian
	 * @return
	 * @throws Exception
	 */
	public static String obtenerHtmlDIANrut(String numNit, String urldian) throws Exception {
		
		String parametrosCompletos = construirParametrosWwwForm(numNit);
		URL url = new URL(urldian);
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		
		conexion.setRequestMethod("POST");
		conexion.setRequestProperty("Content-Type", IContsRequestDIAN.CONTENT_TYPE_FORM_URLENCODE);
		conexion.setDoOutput(true);

		byte[] postData = parametrosCompletos.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		conexion.setRequestProperty("Content-Length", Integer.toString(postDataLength));

		try (OutputStream outputStream = conexion.getOutputStream()) {
			outputStream.write(postData);
		}

		int status = conexion.getResponseCode();
		System.out.println("status : ");
		System.out.println(status);
		String responseDIAN;
		if (status >= 200 && status < 300) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			responseDIAN = content.toString();
		} else {
			System.out.println("Unexpected status " + status);
			responseDIAN = null;
		}

		conexion.disconnect();

		return responseDIAN;
	}
	

	/**
	 * Valida si la conexion a la url es exitosa
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Boolean validarEstadoDIAN(String urlVerificar) throws Exception {
		URL url = new URL(urlVerificar);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int statusCode = connection.getResponseCode();
		Boolean status;
		if (statusCode == 200) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}
	

	/**
	 * @param numNit
	 * @param facesVIEW
	 * @return
	 */
	public static String  construirParametrosWwwForm(String numNit) {
		StringBuilder sbParametros = new StringBuilder();
		String codificacion = "UTF-8";

		try {
			String facesVIEW = obtenerFacesVIEW();
			sbParametros.append(URLEncoder.encode(IContsRequestDIAN.KEY_FORM_CONSULTA_ESTADO_RUT_NUM_NIT, codificacion))
					.append("=").append(numNit).append("&")
					.append(URLEncoder.encode(IContsRequestDIAN.KEY_BTN_BUSCAR_X, codificacion)).append("=")
					.append(IContsRequestDIAN.VALUE_BTN_BUSCAR_X).append("&")
					.append(URLEncoder.encode(IContsRequestDIAN.KEY_BTN_BUSCAR_Y, codificacion)).append("=")
					.append(IContsRequestDIAN.VALUE_BTN_BUSCAR_Y).append("&")
					.append(URLEncoder.encode(IContsRequestDIAN.KEY_COM_SUN_FACES_VIEW, codificacion)).append("=")
					.append(URLEncoder.encode(facesVIEW, codificacion)).append("&")
					.append(URLEncoder.encode(IContsRequestDIAN.KEY_CONSULTA_ESTADO_RUT, codificacion)).append("=")
					.append(URLEncoder.encode(IContsRequestDIAN.VALUE_CONSULTA_ESTADO_RUT, codificacion));

		} catch (Throwable e) {
			return null;
		}

		return sbParametros.toString();

	}
	

	/**
	 * Este metodo hace un peteción a al URL de la DIAN para obtner la propiedad com.sun.faces.VIEW
	 *  y luego poder asociarla en el stringBuilder sbParametros.
	 *  
	 * @return 
	 * @throws Throwable
	 */
	public static  String obtenerFacesVIEW() throws Throwable {
		Boolean conexionExitosa;
		String pruebaVIew;
		try {
			conexionExitosa = validarEstadoDIAN(IContsRequestDIAN.URL_DIAN);
		} catch (Exception e) {
			conexionExitosa = false;
		}

		if (conexionExitosa) {

			// Abrir la conexión e indicar que será de tipo GET
			URL url = new URL(IContsRequestDIAN.URL_DIAN);
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");

			BufferedReader brd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String inputLine;
			StringBuffer contenidoHtml = new StringBuffer();
			
			while ((inputLine = brd.readLine()) != null) {
				contenidoHtml.append(inputLine);
			}
			
			brd.close();
			
			// lectura de html para optener la propiedad faces.VIEW
			Document document = Jsoup.parseBodyFragment(contenidoHtml.toString());
			Element elementoFaces = document.select("input[name=com.sun.faces.VIEW]").first();
			
			pruebaVIew =  elementoFaces.attr("value");
			
			 
		} else {
			pruebaVIew = null;
		}
		
		return pruebaVIew;
	}


}
