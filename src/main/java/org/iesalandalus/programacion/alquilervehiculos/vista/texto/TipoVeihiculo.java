package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVeihiculo {

	TURISMO("Turismo"),
	AUTOBUS("Autobús"),
	FURGONETA("Furgoneta");
	
	
	
	
	private String nombre;
	
	private void TipoVehiculo (String nombre) {
			this.nombre = nombre;
	}
	
	public static TipoVeihiculo get (int ordinal) {
		
		 if (ordinal < 0 || ordinal >= values().length) {
	            throw new IllegalArgumentException("El ordinal proporcionado no es válido.");
		 }
		 
		 return values()[ordinal];
	}

	public static TipoVeihiculo get (Vehiculo vehiculo) {
	
		if (vehiculo == null) {
			throw new IllegalArgumentException("");
		}
		
		for (TipoVeihiculo tipoVehiculo : values()) { // recorro los tipos de vehiculos que hay 
			if (tipoVehiculo.equals(vehiculo)) {
				return tipoVehiculo;
			}
			
		}
		
	}
	
	@Override
	public String toString() {
	
		return super.toString();
	}
	
}
