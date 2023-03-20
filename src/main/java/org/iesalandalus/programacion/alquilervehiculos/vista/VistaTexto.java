package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.util.List;
import java.util.Map;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVeihiculo;

// version 1

public class VistaTexto {

	

	/*public void setControlador(Controlador controlador) {
		return controlador;
		}*/
	
	
	public VistaTexto() {
		
		
		
	}

	public void comenzar() {
		Accion eligeOpcion;

		do {
			Consola.mostrarMenu();
			eligeOpcion = Consola.elegirOpcion();// este metodo ya lee la opcion y la ejecutara
			ejecutar(eligeOpcion);
		} while (eligeOpcion != Accion.SALIR); // si la opcion es diferente de la opcion salir va a seguir mostrando el
												// menu, para poder seguir eligiendo opciones

	}

	public void terminar() {
		System.out.println("Me despido de ti, desde alquiler Sergio");
	}

	
	
	
	
	public void insertarCliente() {
		try {
			Accion accion = Accion.INSERTAR_CLIENTE;
			Consola.mostrarCabecera(accion.toString());
			controlador.insertar(Consola.leerCliente());
			System.out.println("El cliente se ha insertado de forma  correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void insertarVehiculo() {
		try {
			Accion accion = Accion.INSERTAR_TURISMO;
			Consola.mostrarCabecera(accion.toString());
			controlador.insertar(Consola.leerTurismo());
			System.out.println("El turismo se ha insertado correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void insertarAlquiler() {
		try {
			Accion accion = Accion.INSERTAR_ALQUILER;
			Consola.mostrarCabecera(accion.toString());
			controlador.insertar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha insertado de forma correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void buscarCliente() {
		try {
			Accion accion = Accion.BUSCAR_CLIENTE;
			Consola.mostrarCabecera(accion.toString());
			/* controlador.buscar(Consola.leerClienteDni()); como lo tenia antes */
			System.out.println(controlador.buscar(Consola.leerClienteDni()).toString());
			// controlador.getClientes();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarVehicuo() {
		try {
			Accion accion = Accion.BUSCAR_TURISMO;
			Consola.mostrarCabecera(accion.toString());
			System.out.println(controlador.buscar(Consola.leerTurismoMatricula()).toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlquiler() {
		try {
			Accion accion = Accion.BUSCAR_ALQUILER;
			Consola.mostrarCabecera(accion.toString());
			System.out.println(controlador.buscar(Consola.leerAlquiler()).toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	public void modificarCliente() {
		try {
			Accion accion = Accion.MODIFICAR_CLIENTE;
			Consola.mostrarCabecera(accion.toString());
			controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.println("El cliente se ha podidio modificar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void devolverAlquilerCliente() {
		try {
			Accion accion = Accion.DEVOLVER_ALQUILER;
			Consola.mostrarCabecera(accion.toString());
			controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void devolverAlquilerVehiculo() {
		try {
			Accion accion = Accion.DEVOLVER_ALQUILER;
			Consola.mostrarCabecera(accion.toString());
			controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void borrarCliente() {
		try {
			Accion accion = Accion.BORRAR_CLIENTE;
			Consola.mostrarCabecera(accion.toString());
			controlador.borrar(Consola.leerClienteDni());
			System.out.println("El cliente se ha podido borrar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarVehiculo() {
		try {
			Accion accion = Accion.BORRAR_TURISMO;
			Consola.mostrarCabecera(accion.toString());
			controlador.borrar(Consola.leerTurismoMatricula());
			System.out.println("El turismo  se ha podido borrar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlquiler() {
		try {
			Accion accion = Accion.BORRAR_ALQUILER;
			Consola.mostrarCabecera(accion.toString());
			controlador.borrar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha podido borrar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarClientes() {
		try {
			List<Cliente> listarClientesOrdenados = getControlador().getClientes();
			listarClientesOrdenados.sort (Comparator.comparing (Cliente::getNombre) . thenComparing (Cliente::getDni)) :
			for (Cliente cliente : controlador.getClientes()) {
				System.out.println(cliente.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarVehiculos() {
		try {
			Accion accion = Accion.LISTAR_TURISMOS;
			Consola.mostrarCabecera(accion.toString());
			for (Turismo turismo : controlador.getTurismos()) {
				System.out.println(turismo.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarAlquileres() {
		try {
			Accion accion = Accion.LISTAR_TURISMOS;
			Consola.mostrarCabecera(accion.toString());
			for (Turismo turismo : controlador.getTurismos()) {
				System.out.println(turismo.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	public void listarAlquileresClientes() {
		try {

			Accion accion = Accion.LISTAR_ALQUILERES_CLIENTE;
			Consola.mostrarCabecera(accion.toString());
			for (Alquiler alquiler : controlador.getAlquileres(Consola.leerClienteDni())) {
				System.out.println(alquiler.toString());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquileresVehiculo() {
		try {

			Accion accion = Accion.LISTAR_ALQUILERES_TURISMO;
			Consola.mostrarCabecera(accion.toString());
			for (Alquiler alquiler : controlador.getAlquileres(Consola.leerTurismoMatricula())) {
				System.out.println(alquiler.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void mostrarEstadisticasMensualesTipoVehiculo() {
		
	}
	
	
	private Map<TipoVeihiculo, Integer>  inicializarEstadisticas() {
		
	}

	
	
}
