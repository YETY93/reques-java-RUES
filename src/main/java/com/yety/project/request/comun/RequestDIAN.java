package com.yety.project.request.comun;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.yety.project.request.comun.constants.IContsRequestDIAN;

public class RequestDIAN {

	public static String obtenerHtmlDIANrut(String numNit, String urldian) throws Exception {

		String separadorAnd = "&";
		String numNitCompletoParametro = URLEncoder.encode("vistaConsultaEstadoRUT:formConsultaEstadoRUT:numNit", "UTF-8") + "=" + numNit;
		String btnBuscarXParametro = URLEncoder.encode("vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.x", "UTF-8") + "="
				+ IContsRequestDIAN.PARAMETER_BTN_BUSCAR_X;
		String btnBuscarYParametro =  URLEncoder.encode("vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.y" , "UTF-8")  + "="
				+ IContsRequestDIAN.PARAMETER_BTN_BUSCAR_Y;
		String facesViewParametro =  URLEncoder.encode("com.sun.faces.VIEW", "UTF-8")  + "=" + URLEncoder.encode(IContsRequestDIAN.COM_SUN_FACES_VIEW , "UTF-8");
		String ConsultaEstadoRUTParametro = URLEncoder.encode("vistaConsultaEstadoRUT:formConsultaEstadoRUT" , "UTF-8")  + "="
				+ URLEncoder.encode(IContsRequestDIAN.FORM_COMSULTA_ESTADO_RUT, "UTF-8") ;

		String parametrosCompletos = numNitCompletoParametro + separadorAnd + btnBuscarXParametro + separadorAnd
				+ btnBuscarYParametro + separadorAnd + facesViewParametro + separadorAnd + facesViewParametro
				+ separadorAnd + ConsultaEstadoRUTParametro;
		
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

	public void obtenerHtmlDIANgpt()  throws Exception {
			URL url = new URL("https://muisca.dian.gov.co/WebRutMuisca/DefConsultaEstadoRUT.faces");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setDoOutput(true);

			String postData = "vistaConsultaEstadoRUT:formConsultaEstadoRUT:numNit=1049373081&vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.x=0&vistaConsultaEstadoRUT:formConsultaEstadoRUT:btnBuscar.y=0&com.sun.faces.VIEW=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&vistaConsultaEstadoRUT:formConsultaEstadoRUT=vistaConsultaEstadoRUT:formConsultaEstadoRUT";
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(postData);
			out.flush();
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());


	}

	public void obtenerHtmlDIANsonic() throws Exception {
		URL url = new URL("https://muisca.dian.gov.co/WebRutMuisca/DefConsultaEstadoRUT.faces");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setDoOutput(true);

		String query = "vistaConsultaEstadoRUT%3AformConsultaEstadoRUT%3AnumNit=1049373081&vistaConsultaEstadoRUT%3AformConsultaEstadoRUT%3AbtnBuscar.x=0&vistaConsultaEstadoRUT%3AformConsultaEstadoRUT%3AbtnBuscar.y=0&com.sun.faces.VIEW=H4sIAAAAAAAAAO1cXWwc13WeXZISSSuyHcVqUpvxyLJFGxGXy6V%2B%2BG9TpBSy5Y9CUmoaw1nf3bm7HGV27vjOneVStgOnD81DHhIgCdAWTtsAeUmQvDRNWiABgkBAAhRwgTjoS4G2QV6CoIkd5yVtX9pz7vzszO7skrMa1pGheRjOzsw9957v%2FN4z9%2FKbv1YGbK7MMF7NkVukceGWXckRyzL0MhE6M3OLcHNbEEHXiUmqlD%2B5wyndFtwRDqcbTKN%2F9vm3%2F%2BJO5ezQCUVpWLcVpV9RzkaolVnNYiY1haR1U6d7W4wJZahY9y%2FdI6OM3iJ10shVSJnaoWY3VjdITTerS8wURDcpV07XdVsQ%2BG07hiBX4VpjWzd2PEIDypUyy1VZPafpxMzVHN0ukxzhLzm6oGUYN8nt0VIuMjbocHlF1IzrxKTGR7muKf1FXcsr%2FnG1Z4qbjrAccY3xGhGSaOEoiE4GROd7JorklEcqcO6E7KCy3DP1VRNGvKJrGjWVR2tMY9c5teE5KYOabVODlvHiymbASDpdnbD1qqPjwxtbaynTPt3KBiKYOgensJdNi%2FKj6%2BLhGoEHYFlLHBpyndlBB4vJO5BOwOtlnZo2uUVtqaQXAqppGehFj15%2FahQvBd5o6S6tc4c2XNu8nDrbU6mzPR1QTIXtASA5kU%2BBb6mlkuQx06lt6CKgmd44J9KlmdXqqQpcjtEPGsMp8j2ZLt8PcHIbPDkr68RQFDcTSFtUF9Id8kmL6zXKFy1qGLrGjkC3LqZL80GbVh1TY0c44kvp0jzhQrzBaiVOj2C4l1MeLhOc2e5om0EwNTNuOu70EJhOH9VCPh2a0glECKfsa49RmaGmLqiC729TFFQhZX8raabsECXNlH2WpNn0KqmJqGn5Kc2jJNWp9AfqG2ifcq1nkkusBjm6dsURgpnKUEmYVxxozYPRpkUaRzzZNP%2Fe5xce2TV9zzA%2FpQzDaU2vWXpoyCmKbTLwKw2HK6eeX8NKQs4gZjW3WboF5Ga%2F8M8f%2F5uH7GeMLJYp4L2s85LyaSXb4ao%2FuBq0ufKwpOYI3citEHt3nVgDx%2F%2F1h3dOv%2FiTPiV7DXhjRLtGyoLxVWVI7ELk2GWG1rCefU4OKbM3iMTxSihPhmscwCCxrNzSja2tqxs7xZurV%2F%2BkuLW5uYOdDzescJEkfB0MSTJ4hTGDEvMNlb%2F2L6%2F%2Fz1tZJfMJZaBODIc2rIyNpN6nWEBreGVnfa14ZXF7dUkoj44v00rbND8HLFIbyD%2FU5HiNlYlBP%2F1fD7%2F4ev6%2Ff9WnHFtVBncBhTLT6JpyvMwcU%2FB9obxfYj6OQxrfFlw3q7NryjA4AJgBwrxVDuTUmjKILzikSr3fx%2Bwy1y3h%2FTpeJxy0wf3ZsP4XDkiob2xD2g9%2FqSRyWp4agYQG8OJBebKal8HjTIx4ByWhwTj5qHHy2Vy%2FvrmBElpd3gZ0TjXRWeSc7K%2Fptmh85s2Rv%2Fwx%2BUqfkllV%2Bm39NpVqNrCHWfCAOxHMewUgr2QjOhRbfJVuoAZ0KHV1fICMnXTBsJoQxWDw%2BMFo4O0R%2BfDD8kG%2FCxEO3h3co%2FFjmEFesZnaHI1wdcpHFr2jZUUVeRUMvEr5%2B3%2F%2Bt1%2F73Wc%2BO5VFJD1F9jVSvrfh1EqU%2F%2Fk3vzzywJd%2B9jlp0K%2FByDymLTngvD%2F%2Bk1bs0VQPKwabY8FVXweUFInSQ9LjdAeigM3OhIE4gz6NU1OjHPxZ2JdtyZuUg7n2fX5OkJJBVdsBP8r350dH1bJBbHt%2B1CKaRrUx%2BXhU3dM1sTs%2FOpHPPzWq2mLfoPOjJcaBzkx%2BdnRheGhoSPUPuJ4TJabtt92G%2B7z9JtzV%2FF5repXYRYuYQS8VZoqxCqnpxv7MIpitcV5doUadCr1Mzqs2Me0xm3K9MqvKN9EmZiYKViNmVNiVDbSjnWkM4BhdOKe%2F5ECeN7t8jpGyI%2Bgs3FXhBtt%2FdkadG8d2hySom3pZZ6MLc0QFL12ZH3Vv5HYhpqTB1qokNzdOFhINy6YW4aAxfHThFZ%2BhuNELWhtduGE7BCt0KqdVUDpsZ3fsbW5caDGyHo8T9sEaIEN0kXBKihYFmZgkXpK7EwueKaD6qm6OrmJ8Ae7gYWybwsKWyxBT7%2FwV4MjUHa6XHIHM5qBZ4cjY8%2BSOZgVRa8ygFTEzlbca6hlIWBgXEI9AtkFoORl2H8oRe49JbPZE03vgadaygjh4uBGdTurrB6WvHxRdK%2FUiWlwXnerhIraELWKqzm5sueD%2Buehl5NMy1JzvgFFsBEU2zoZd7ociLhcH0HS1mBudCX3yGm%2BM7e3tjSHhMYcb1MRMR%2BscQQa7ZpLN9GM4kVqESLUktF5y9a03b%2F7ilyMvf9RPaCEwu8Gy2TWE13Pxn9TkF70VSFIp3yZ1yv%2F0n749%2F8XX31jPKtk1ZUja%2Bwap%2BTnasA3vaLJNNM%2FzcmuUzxkgnrMd0%2BuKGrmbOJgruolGtVqzDKGMldsSzq7qtZJE5jNdSCEffxTWh0cj%2BuB%2BBQlpRDONbNpYKMv0coz3KV2NLlUdwKtP%2Bnb6YkjEeFXCE4jmw%2B34Rg10OhGg4bbYwx9Hcdi9d4F6%2BmBF9J3U0l3poEsFe117z4D3VDx4be79ucTItZDA%2FtbfM7CdjYGtPf4tJAOtlQD2tXFEkDWn1McPl9ZMJOIFAz8S2wz76ZGIn%2FY%2FJgeeOng19uTGaUgmDBhHBIUD0qXEU%2BN%2BmS71u3nLJffPlJe%2BXPYLi42eMLmIfVyPSmw8NMmV096TQslAt5m8d%2F81pTkdbjkg7cK%2BHAPz6qKb0uPdvkv5fEz5K616weXeuL%2BExL5zEPd4%2Bh6evn8Y%2Fj%2FQxn9RaK52%2FKBr1eguMvzEjF9GYj8Km0I0hcXKeqhaoDxznXG1Qupwtp0aTGthIkVVA6aJQRS3VY0I10X4NE%2BUmYEzx5lCw5o9nBe4O9uY9r5ZFby%2FvpEUelOPKezkux3UI6wEh1CLB0LT23B6n4otBCC4CyOC1Qz%2BCgS5aqA3DKaxj387GAOhfKTCTI3t6MIx2BpMchdvOZLQeaFb7JpukA0sQhk3KddoYGH9IVfacQoUh1Gv1pI0rp7mjijWvMgAM6QSNYomIltIiuSErFn%2BZxTKn3qBw6%2BpHspIjsdoytFmFx9szy58DZtMhIPbCqn%2BKh6IrnlENMoGShSOx%2F7RfC947UEvlg3LmubMxMX8rDTNIY7VJq5rDAMVDO%2BgekeKupcVyqDrdakWFUA2IoDH2gWg1dUz86rpGEbkzUdiFFar96KvE0jx1ynoa5p4wUVmJB2kPhD3plDGEwGl1ZHaW91QAg1zNW46f1RRcFgGgGF%2F5VV0tZS%2FuKltQZK%2FiKh94Y%2B%2FWKdlgY2%2FKKZlIYuMLMn1S366%2BPeDQwv%2BeecwEQOf%2FCDGI75Lmvixdv0KScVXSfXcOTXGu0pkl1jNMqhg6nyM%2Bj4eY%2BiSftH2xJ5cJFgPzszdayZ%2FxECPdCUvlKlEMIeaImPzhwI783sNdguGXfEOCyYO7CditNp1QUUSOKfkio1z%2F8zCe0Cx08Q6pmYUJf98%2FgWhzCYCOxphkMNn72t4FPUYDffC712pOJZyMs%2FdV%2FGEKj4BKj6X7MNFNFdCFhfv63iEvNrZi5teJplcwbFal7lyX8ETKnjhhaRf5sIpP%2FK3dF%2B7D9RuOR%2FylNvuRbuxJJtZvq%2FdCbV7MrF2h2euyN%2FVFLT7Liu20%2F6%2BCH8bQ7D1oMeptaxc%2F0eUsYOn1lnZ4BBT6yMuyKZZ5BmJsdUKLRdJWTi9zZGxIp55%2B14z1ASItb8JgO2SxV4RK2DhO%2FObVsTcToXyVHQNkVmnXOSWiaA7EIWW3N%2BUN7ESynGNVgh0A6ZxStPG1tfH9uFQV1ZmarUZG%2BlmP4gfZjUg8nsulpjVDoI2BHN%2FxsrnQzEa7TqLXmSDRd7MO%2FeaNvcAW4xah5ok%2FY7hAo7j%2FO27Hj4Kk%2F5uLX%2BHlb8rqrfoUZCF2Z8ljR6HWg%2F%2F%2F%2FApLzMSPIh8xMp8ol0DdI16P%2FzFwZCFTBQmJ%2FKTk9PqK6%2FEpCxdmlzIQ5NhNeYYGkpGaCJ534W0%2Br6QuG9UwORahrXmbPt0Tigk6AFCjl5nM%2BoS45CsWbheXyVy2cHG6o76kkNVm6rULDvUFJyYal2vuksRqKkaRC0ReAxN5MIEvIB7y6uLG7l307l1U1FlPBHu05d6wR1Lodn2KZ3ImEEP245tUVPTtY7Yl519ouqm3F1V1u9824xIQhUUl7YTo4biUG2fHDmvwm0Vt4%2BY6i1H02Xiz1SieWtKCMibgKTKBu5YIPJt4gjGoa2m4gJjKiRJBiMgJrj6GsX9fXAHcNAFtlXruH1CL5NgXAgqri6WGgM6oOkcl%2B7iU91EoPyuqOHxBPMWk6m0AWPCvvDahjdsHbfMOCUgzg2GWgYtNFYDPgydBcRYTn16EZIYdeIijMdQl2mZ41SocOFSHgdYyE9MPvNeUUKMOsmVEIuV2fbZrlAqQQ%2FqEjHL1CCdlbDdAegmkS7j3vUApWRBaiqxs56%2B2Iu8sPaWbZ8piwxDQWwwldPAcgD6dbnYqUVuhhSa5q4bKxveqjEUoonmDBN6nXJmIjsoJ1Yy9KpnxNDIJV%2FSuZQ4Gh6MDR6YyJ1H1DLIbZS9LTevlcF%2F2d67gQlenprCt0mV2YK5xpi%2FoO5L8weTxt040t6jeuXOFvXbgIidU1XAxlNBeNNiGr%2FzVRMQkG9w1dTNqmMSt5E7fumhgKemo3PvC38PElF3CYxaoiGhIC4OyEoYieSK2ym3bFlCmFwl5BrCf%2BiQJSpelnjSCt3plCHiDp72zV%2BtRYdU9kIlZ1OWUv4xyuYsjnlgbrzEE%2BzbSpzk98kkv0%2BE9uN7e3fzIrzjvccsX67s%2B9jBWX5Tfp3qQzBCMHJQUKAeRqEvBoWRGNl12S99wi33LcqdNkL5Q5j75paXahrcQK5897bliFhPC3jpNVKlHZztVZxKyxdMmEo73CiSks0MsMjg7qtnX4685F4US0wwOHtCuZQI%2FECa6FNfbwqgdd%2BQ%2By8TgmW4fr3kibatUOtU7DIttBeqOd0CRcHo4HYe4f3htqgBcnz87MvtwaRJ4NXQsjqhHHO5sKJL3%2BUaaPC1Dod8HDyiRviGLp5%2BZralmhh%2B%2F5jtlGq66Go%2BiRQHkf06nr7Rqb6Q%2FVY0CkfrC9faFINDJoLJ6eEUw3At8%2FAVuB4MeBLLatmvRAw4%2B3dt6LZi%2FQfe2EAinpQqxLBpZ%2FEg2e93Fc1jvYhGOY1u63Iirps%2BD6n8ddh01IjpBP8dxPxUi%2F1gw78P28fxOFm1zqHjbOUjZ18%2B6K2cR%2FvVFkhjDtxHQisiUixJJeI91mX3r1zh%2BWRMbLsWu%2FU5bucz3Gvb8Y83MQHrFByt%2FwOEWwkDF1YAAA%3D%3D&vistaConsultaEstadoRUT%3AformConsultaEstadoRUT=vistaConsultaEstadoRUT%3AformConsultaEstadoRUT";
		byte[] postData = query.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		con.setRequestProperty("Content-Length", Integer.toString(postDataLength));

		try (OutputStream outputStream = con.getOutputStream()) {
		    outputStream.write(postData);
		}

		int status = con.getResponseCode();
		if (status >= 200 && status < 300) {
		    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String inputLine;
		    StringBuffer content = new StringBuffer();
		    while ((inputLine = in.readLine()) != null) {
		        content.append(inputLine);
		    }
		    in.close();
		    System.out.println(content.toString());
		} else {
		    System.out.println("Unexpected status " + status);
		}

		con.disconnect();
	}

}
