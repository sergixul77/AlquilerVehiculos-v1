package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;

public abstract class Vista {

	
	private Controlador controlador;
	
	protected  Controlador getControlador() {	
		
		return controlador;
	}
	
	public void setControlador(Controlador controlador ) {
		if (controlador != null) { // si el controlador es diferente de null
			this.controlador = controlador;
		}	
	}
	
	
	public void comenzar() {
		Accion eligeOpcion;

		do {
			Consola.mostrarMenuAcciones();
			eligeOpcion = Consola.elegirAccion();// este metodo ya lee la opcion y la ejecutara
			eligeOpcion.ejecutar();
		} while (eligeOpcion != Accion.SALIR); // si la opcion es diferente de la opcion salir va a seguir mostrando el
												// menu, para poder seguir eligiendo opciones

	}
	
	public void terminar() {
		System.out.println("Me despido de ti, desde alquiler Sergio");
	}
}
