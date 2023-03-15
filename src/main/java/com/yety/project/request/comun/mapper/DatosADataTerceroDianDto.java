package com.yety.project.request.comun.mapper;

import com.yety.project.request.comun.dto.DataTerceroDianDto;

/**
 * @author Yesid R
 *
 */
public abstract class DatosADataTerceroDianDto {
	
	/**
	 * @param primerNombre
	 * @param otroNombre
	 * @param primerApellido
	 * @param otroApellido
	 * @return
	 */
	public static DataTerceroDianDto crearTerceroNatural(String primerNombre, String otroNombre, String primerApellido,
													String otroApellido) {
		
		DataTerceroDianDto dataTerceroDianDto = new DataTerceroDianDto();
		
		dataTerceroDianDto.setPrimerNombre(primerNombre);
		dataTerceroDianDto.setOtrosNombres(otroNombre);
		dataTerceroDianDto.setPrimerApellido(primerApellido);
		dataTerceroDianDto.setOtrosApellido(otroApellido);
		dataTerceroDianDto.setRazonSocial("");
		dataTerceroDianDto.setEsJuridica(false);
		
		return dataTerceroDianDto;
		
	}
	
	/**
	 * @param nombreRazonSocial
	 * @return
	 */
	public static DataTerceroDianDto crearTerceroJuridica(String nombreRazonSocial) {
		
		DataTerceroDianDto dataTerceroDianDto = new DataTerceroDianDto();
		/*
		dataTerceroDianDto.setPrimerNombre("");
		dataTerceroDianDto.setOtrosNombres("");
		dataTerceroDianDto.setPrimerApellido("");
		dataTerceroDianDto.setOtrosApellido("");
		*/
		dataTerceroDianDto.setRazonSocial(nombreRazonSocial);
		dataTerceroDianDto.setEsJuridica(true);
		
		return dataTerceroDianDto;
		
	}
	
	
	
}
