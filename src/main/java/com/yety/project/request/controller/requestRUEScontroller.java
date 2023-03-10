package com.yety.project.request.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yety.project.request.comun.RequestDIAN;
import com.yety.project.request.comun.RequestRUES;

@RestController
@RequestMapping("/consultarues/")
public class requestRUEScontroller {
	
	@GetMapping("nit/")
    public String paginaComun() throws Exception {
		//String url = "https://www.rues.org.co/RM/";
		String url = "https://muisca.dian.gov.co/WebRutMuisca/DefConsultaEstadoRUT.faces";
		RequestRUES requestRUES = new RequestRUES();
		
		//requestRUES.obtenerCokie(url);	
		//requestRUES.obtenerCookieCM(url);
		//System.out.println("###########");
		String respuesta = RequestDIAN.obtenerHtmlDIANrut("1049373081", url);
		
		return respuesta;
    }

}
