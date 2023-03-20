package org.iesalandalus.programacion.alquilervehiculos.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.Vehiculos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModeloTest {
	
	private static final String MENSAJE_ERROR_INSERTAR_ALQUILER_NULO = "ERROR: No se puede realizar un alquiler nulo.";
	
	@InjectMocks
	private Modelo modeloCascada = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
	
	@Mock
	private static IFuenteDatos fuenteDatos;

	@Mock
	private static IClientes clientes;
	@Mock
	private static IVehiculos vehiculos;
	@Mock
	private static IAlquileres alquileres;

	private static Cliente cliente;
	private static Turismo turismo;
	private static Alquiler alquiler;
	private static LocalDate hoy;
	private static LocalDate ayer;

	@BeforeAll
	public static void setup() {
		fuenteDatos = mock();
		//mockConstruction(FuenteDatosMemoria.class);
		//mockConstruction(Clientes.class);
		when(fuenteDatos.crearClientes()).thenReturn(clientes);
		when(fuenteDatos.crearVehiculos()).thenReturn(vehiculos);
		when(fuenteDatos.crearAlquileres()).thenReturn(alquileres);
		cliente = mock();
		mockConstruction(Cliente.class);
		when(cliente.getNombre()).thenReturn("Bob Esponja");
		when(cliente.getDni()).thenReturn("11223344B");
		when(cliente.getTelefono()).thenReturn("950112233");
		turismo = mock();
		mockConstruction(Vehiculos.class);
		mockConstruction(Turismo.class);
		when(turismo.getMarca()).thenReturn("Seat");
		when(turismo.getModelo()).thenReturn("León");
		when(turismo.getMatricula()).thenReturn("1234BCD");
		when(turismo.getCilindrada()).thenReturn(90);
		hoy = LocalDate.now();
		ayer = hoy.minusDays(1);
		alquiler = mock();
		mockConstruction(Alquileres.class);
		mockConstruction(Alquiler.class);
		when(alquiler.getCliente()).thenReturn(cliente);
		when(alquiler.getVehiculo()).thenReturn(turismo);
		when(alquiler.getFechaAlquiler()).thenReturn(ayer);
	}

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void comenzarLlamacreaClientescreaVehiculoscrearAlquileres() {
		modeloCascada.comenzar();
		verify(fuenteDatos).crearClientes();
		verify(fuenteDatos).crearVehiculos();
		verify(fuenteDatos).crearAlquileres();
	}
	
	@Test
	void terminarNoHaceNada() {
		assertDoesNotThrow(() -> modeloCascada.terminar());
	}

	@Test
	void insertarClienteLlamaClientesInsertar() {
		assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
		assertDoesNotThrow(() -> verify(clientes).insertar(any(Cliente.class)));
		assertNotSame(cliente, modeloCascada.buscar(cliente));
	}

	@Test
	void insertarVehiculoLlamaVehiculosInsertar() {
		assertDoesNotThrow(() -> modeloCascada.insertar(turismo));
		assertDoesNotThrow(() -> verify(vehiculos).insertar(any(Vehiculo.class)));
		assertNotSame(turismo, modeloCascada.buscar(turismo));
	}

	@Test
	void insertarAlquilerLlamaClientesBuscarVehiculosBuscarAlquileresInsertar() {
		InOrder orden = inOrder(clientes, vehiculos, alquileres);
		when(clientes.buscar(cliente)).thenReturn(cliente);
		when(vehiculos.buscar(turismo)).thenReturn(turismo);
		assertDoesNotThrow(() -> modeloCascada.insertar(alquiler));
		orden.verify(clientes).buscar(cliente);
		orden.verify(vehiculos).buscar(turismo);
		assertDoesNotThrow(() -> orden.verify(alquileres).insertar(any(Alquiler.class)));
		assertNotSame(alquiler, modeloCascada.buscar(alquiler));
	}
	
	@Test
	void insertarAlquilerAlquilerNuloLanzaExcepcion() {
		Alquiler alquilerNulo = null;
		NullPointerException npe = assertThrows(NullPointerException.class, () -> modeloCascada.insertar(alquilerNulo));
		assertEquals(npe.getMessage(), MENSAJE_ERROR_INSERTAR_ALQUILER_NULO);
	}

	@Test
	void buscarClienteLlamaClientesBuscar() {
		assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
		Cliente clienteBuscado = modeloCascada.buscar(cliente);
		verify(clientes).buscar(cliente);
		assertNotSame(cliente, clienteBuscado);
	}

	@Test
	void buscarVehiculoLlamaVehiculosBuscar() {
		assertDoesNotThrow(() -> modeloCascada.insertar(turismo));
		Vehiculo vehiculoBuscado = modeloCascada.buscar(turismo);
		verify(vehiculos).buscar(turismo);
		assertNotSame(turismo, vehiculoBuscado);
	}

	@Test
	void buscarAlquilerLlamaAlquileresBuscar() {
		assertDoesNotThrow(() -> modeloCascada.insertar(cliente));
		when(clientes.buscar(cliente)).thenReturn(cliente);
		when(vehiculos.buscar(turismo)).thenReturn(turismo);
		Alquiler alquilerBuscado = modeloCascada.buscar(alquiler);
		verify(alquileres).buscar(alquiler);
		assertNotSame(alquiler, alquilerBuscado);
	}

	@Test
	void modificarClienteLlamaClientesBuscarModificar() {
		assertDoesNotThrow(() -> modeloCascada.modificar(cliente, "Patricio Estrella", "950123456"));
		assertDoesNotThrow(() -> verify(clientes).modificar(cliente, "Patricio Estrella", "950123456"));
	}
	
	@Test
	void devolverClienteLlamaAlquileresDevolverCliente() {
		assertDoesNotThrow(() -> modeloCascada.devolver(cliente, hoy));
		assertDoesNotThrow(() -> verify(alquileres).devolver(cliente, hoy));
	}
	
	@Test
	void devolverVehiculoLlamaAlquileresDevolverVehiculo() {
		assertDoesNotThrow(() -> modeloCascada.devolver(turismo, hoy));
		assertDoesNotThrow(() -> verify(alquileres).devolver(turismo, hoy));
	}

	@Test
	void borrarClienteLlamaAlquileresGetPrestamosBorrarClientesBorrar() {
		simularClienteConAlquileres();
		InOrder orden = inOrder(clientes, alquileres);
		assertDoesNotThrow(() -> modeloCascada.borrar(cliente));
		orden.verify(alquileres).get(cliente);
		for (Alquiler alquiler : alquileres.get(cliente)) {
			assertDoesNotThrow(() -> orden.verify(alquileres).borrar(alquiler));
		}
		assertDoesNotThrow(() -> orden.verify(clientes).borrar(cliente));
	}

	private void simularClienteConAlquileres() {
		List<Alquiler> alquileresCliente = new ArrayList<>();
		Alquiler alquiler1 = mock();
		Alquiler alquiler2 = mock();
		alquileresCliente.add(alquiler1);
		alquileresCliente.add(alquiler2);
		when(alquileres.get(cliente)).thenReturn(alquileresCliente);
	}

	@Test
	void borrarVehiculoLlamaAlquileresGetPrestamosBorrarVehiculosBorrar() {
		simularVehiculoConAlquileres();
		InOrder orden = inOrder(vehiculos, alquileres);
		assertDoesNotThrow(() -> modeloCascada.borrar(turismo));
		orden.verify(alquileres).get(turismo);
		for (Alquiler alquiler : alquileres.get(turismo)) {
			assertDoesNotThrow(() -> orden.verify(alquileres).borrar(alquiler));
		}
		assertDoesNotThrow(() -> orden.verify(vehiculos).borrar(turismo));
	}

	private void simularVehiculoConAlquileres() {
		List<Alquiler> alquileresVehiculo = new ArrayList<>();
		Alquiler alquiler1 = mock();
		Alquiler alquiler2 = mock();
		alquileresVehiculo.add(alquiler1);
		alquileresVehiculo.add(alquiler2);
		when(alquileres.get(turismo)).thenReturn(alquileresVehiculo);
	}
	
	@Test
	void borrarAlquilerLllamaAlquileresBorrar() {
		when(alquileres.buscar(alquiler)).thenReturn(alquiler);
		assertDoesNotThrow(() -> modeloCascada.borrar(alquiler));
		assertDoesNotThrow(() -> verify(alquileres).borrar(alquiler));
	}
	
	@Test
	void getClientesLlamaClientesGet() {
		List<Cliente> clientesDevueltos = new ArrayList<>();
		clientesDevueltos.add(cliente);
		when(clientes.get()).thenReturn(clientesDevueltos);
		List<Cliente> clientesExistentes = modeloCascada.getListaClientes();
		verify(clientes).get();
		assertNotSame(cliente, clientesExistentes.get(0));
	}
	
	@Test
	void getVehiculossLlamaVehiculosGet() {
		List<Vehiculo> vehiculosDevueltos = new ArrayList<>();
		vehiculosDevueltos.add(turismo);
		when(vehiculos.get()).thenReturn(vehiculosDevueltos);
		List<Vehiculo> vehiculosExistentes = modeloCascada.getListaVehiculos();
		verify(vehiculos).get();
		assertNotSame(turismo, vehiculosExistentes.get(0));
	}
	
	@Test
	void getAlquileresLlamaAlquileresGet() {
		List<Alquiler> alquileresDevueltos = new ArrayList<>();
		alquileresDevueltos.add(alquiler);
		when(alquileres.get()).thenReturn(alquileresDevueltos);
		List<Alquiler> alquileresExistentes = modeloCascada.getListaAlquileres();
		verify(alquileres).get();
		assertNotSame(alquiler, alquileresExistentes.get(0));
	}
	
	@Test
	void getAlquileresClienteLlamaAlquileresGetCliente() {
		List<Alquiler> alquileresDevueltos = new ArrayList<>();
		alquileresDevueltos.add(alquiler);
		when(alquileres.get(cliente)).thenReturn(alquileresDevueltos);
		List<Alquiler> alquileresCliente = modeloCascada.getListaAlquileres(cliente);
		verify(alquileres).get(cliente);
		assertNotSame(alquiler, alquileresCliente.get(0));
	}
	
	@Test
	void getAlquileresVehiculoLlamaAlquileresGetVehiculoo() {
		List<Alquiler> alquileresDevueltos = new ArrayList<>();
		alquileresDevueltos.add(alquiler);
		when(alquileres.get(turismo)).thenReturn(alquileresDevueltos);
		List<Alquiler> alquileresTurismo = modeloCascada.getListaAlquileres(turismo);
		verify(alquileres).get(turismo);
		assertNotSame(alquiler, alquileresTurismo.get(0));
	}

}
