package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum FactoriaVista {

	TEXTO {
		@Override
		public VistaTexto crear() {
			return new VistaTexto();
		}
	};

	public abstract VistaTexto crear();

}
