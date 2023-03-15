package com.yety.project.request.comun.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yesid R
 *
 */
@Getter
@Setter
public class DataTerceroDianDto implements Serializable {

	/**
	 * Serealizable
	 */
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = -4975430391421108695L;

	private String primerNombre;
	
	private String otrosNombres;
	
	private String primerApellido;
	
	private String otrosApellido;
	
	private String razonSocial;
	
	private Boolean esJuridica;
	
}
