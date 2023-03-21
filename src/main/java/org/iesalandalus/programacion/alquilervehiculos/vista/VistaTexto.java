package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVeihiculo;

// version 1

public class VistaTexto extends Vista {

	

	public VistaTexto() {
		super();
		Accion.setVista(this);

	}

	public void comenzar() {
		Accion eligeOpcion;

		do {
			Consola.mostrarMenuAcciones();
			eligeOpcion = Consola.elegirAccion();// este metodo ya lee la opcion y la ejecutara
			Consola.mostrarCabecera(eligeOpcion.toString());
			eligeOpcion.ejecutar();
		} while (eligeOpcion != Accion.SALIR); // si la opcion es diferente de la opcion salir va a seguir mostrando el
												// menu, para poder seguir eligiendo opciones

	}

	public void terminar() {
		System.out.println("Me despido de ti, desde alquiler Sergio");
	}

	public void insertarCliente() {
		try {
			getControlador().insertar(Consola.leerCliente());
			System.out.println("El cliente se ha insertado de forma  correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarVehiculo() {
		try {
			getControlador().insertar(Consola.leerVehiculo());
			System.out.println("El turismo se ha insertado correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarAlquiler() {
		try {
			getControlador().insertar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha insertado de forma correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarCliente() {
		try {

			getControlador().buscar(Consola.leerClienteDni());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarVehicuo() {
		try {
			getControlador().buscar(Consola.leerVehiculoMatricula());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlquiler() {
		try {
			getControlador().buscar(Consola.leerAlquiler());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void modificarCliente() {
		try {
			getControlador().modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.println("El cliente se ha podidio modificar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverAlquilerCliente() {
		try {
			getControlador().devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverAlquilerVehiculo() {
		try {
			getControlador().devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCliente() {
		try {
			getControlador().borrar(Consola.leerClienteDni());
			System.out.println("El cliente se ha podido borrar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarVehiculo() {
		try {
			getControlador().borrar(Consola.leerVehiculoMatricula());
			System.out.println("El turismo  se ha podido borrar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlquiler() {
		try {
			getControlador().borrar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha podido borrar correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarClientes() {
		try {
			List<Cliente> listaDeClientesOrdenada = getControlador().getClientes();
			listaDeClientesOrdenada.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
			for (Cliente cliente : listaDeClientesOrdenada) {
				System.out.println(cliente.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarVehiculos() {
		try {
			List<Vehiculo> listaDeVehiculosOrdenada = getControlador().getVehiculos();
			listaDeVehiculosOrdenada.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo));
			for (Vehiculo vehiculo : listaDeVehiculosOrdenada) {
				System.out.println(vehiculo.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquileres() {
		try {
			List<Alquiler> listaDeAlquileresOrdenada = getControlador().getAlquileres();
			listaDeAlquileresOrdenada
					.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getFechaDevolucion));
			for (Alquiler alquiler : listaDeAlquileresOrdenada) {
				System.out.println(alquiler.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquileresClientes() {
		try {

			List<Alquiler> listaDeAlquileresCliente = getControlador().getAlquileres(Consola.leerClienteDni());
			for (Alquiler alquiler : listaDeAlquileresCliente) {
				System.out.println(alquiler.toString());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquileresVehiculo() {
		try {

			List<Alquiler> listaDeAlquileresVehiculo = getControlador().getAlquileres(Consola.leerVehiculoMatricula());
			for (Alquiler alquiler : listaDeAlquileresVehiculo) {
				System.out.println(alquiler.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {

	}

	private Map<TipoVeihiculo, Integer> inicializarEstadisticas() {

	}

}
