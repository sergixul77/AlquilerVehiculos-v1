package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class Controlador {


	private Modelo modelo;

	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: no puedes crear un modelo nulo");
		}
		if (vista == null) {
			throw new NullPointerException("ERROR: no puedes crear una vista  nula");
		}

		this.modelo = modelo;
		this.vista = vista;
		vista.setControlador(this); // para que vista pueda mandar a ejecutar opciones al controlador y pueda
									// manejarla correctamente. Con el this te estas refiriendo a una instancia de
									// la clase?

	}

	public void comenzar() {
		modelo.comenzar(); // tengo que poner primero modelo,
		vista.comenzar();

	}

	public void terminar() {
		modelo.terminar();
		vista.terminar();

	}

	/*------       Inserta un cliente o un turismo o un alquiler           --------*/

	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		modelo.insertar(cliente);
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {

		modelo.insertar(turismo);

	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		modelo.insertar(alquiler);
	}

	/*------       Busca un cliente o un turismo o un alquiler           --------*/

	public Cliente buscar(Cliente cliente) {

		return modelo.buscar(cliente);

	}

	public Turismo buscar(Turismo turismo) {

		return modelo.buscar(turismo);

	}

	public Alquiler buscar(Alquiler alquiler) {

		return modelo.buscar(alquiler);

	}

	/* Modifica un clinente el nombre */

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		modelo.modificar(cliente, nombre, telefono);

	}

	/* Devuelve el alquiler con la fecha de devolucion */

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		modelo.devolver(alquiler, fechaDevolucion);

	}

	/* Borrar un cliente, un turismo y un alquiler */

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		modelo.borrar(turismo);

	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		modelo.borrar(cliente);

	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		modelo.borrar(alquiler);

	}

	public List<Cliente> getClientes() {
		return modelo.getClientes();
	}

	public List<Turismo> getTurismos() {
		return modelo.getTurismos();
	}

	public List<Alquiler> getAlquileres() {

		return modelo.getAlquileres();
	}

	public List<Alquiler> getAlquileres(Cliente cliente) {

		return modelo.getAlquileres(cliente);

	}

	public List<Alquiler> getAlquileres(Turismo turismo) {
		return modelo.getAlquileres(turismo);

	}

}