package rss;

import java.util.*;

/**
 * 
 * @author Alvaro
 *
 */
public class Menu {

	static Scanner entrada = new Scanner(System.in);

	/**
	 * Constructor
	 */
	public Menu() {
		super();
	}

	/**
	 * Muestra titulo, link y descripción de canal y titulo, link y descripción de
	 * los elementos.
	 * 
	 * @param miCanal
	 */
	public void mostrarTodaInfoCanal(Canal miCanal) {

		mostrarInfoCanal(miCanal);
		System.out.println("---------------------------------------------------");
		System.out.println("|          DATOS ACTUALES DE LOS ELEMENTOS        |");
		System.out.println("---------------------------------------------------");
		System.out.println(miCanal.ListaElementos);

	}

	/**
	 * Modifica la información de titulo, link y descripción de un elemento.
	 * 
	 * @param elemento
	 */
	public void modificarInfoItem(Elemento elemento) {

		System.out.print("[+] Introduce el titulo que quieres poner: ");
		elemento.setTitulo(entrada.next());
		System.out.print("[+] Introduce el link que quieres poner: ");
		elemento.setLink(entrada.next());
		System.out.print("[+] Introduce la descripcion que quieres poner: ");
		elemento.setDescripcion(entrada.next());

	}

	/**
	 * Muestra titulo, link y descripción de un elemento.
	 * 
	 * @param elemento
	 */
	public void mostrarInfoItem(Elemento elemento) {

		System.out.println("---------------------------------------------");
		System.out.println("|          DATOS ACTUALES DEL ELEMENTO      |");
		System.out.println("---------------------------------------------");
		System.out.println("-TITULO-");
		System.out.println(elemento.getTitulo());
		System.out.println("-LINK-");
		System.out.println(elemento.getLink());
		System.out.println("-DESCRIPCIÓN-");
		System.out.println(elemento.getDescripcion());

	}

	/**
	 * Modifica la información de titulo, link y descripción del canal.
	 * 
	 * @param miCanal
	 */
	public void modificarInfoCanal(Canal miCanal) {

		System.out.print("[+] Introduce el titulo que quieres poner: ");
		miCanal.setTitulo(entrada.next());
		System.out.print("[+] Introduce el link que quieres poner: ");
		miCanal.setLink(entrada.next());
		System.out.print("[+] Introduce la descripcion que quieres poner: ");
		miCanal.setDescripcion(entrada.next());

	}

	/**
	 * Muestra titulo, link y descripción del canal.
	 * 
	 * @param miCanal
	 */
	public void mostrarInfoCanal(Canal miCanal) {

		System.out.println("--------------------------------------------");
		System.out.println("|          DATOS ACTUALES DEL CANAL        |");
		System.out.println("--------------------------------------------");
		System.out.println("-TITULO-");
		System.out.println(miCanal.getTitulo());
		System.out.println("-LINK-");
		System.out.println(miCanal.getLink());
		System.out.println("-DESCRIPCIÓN-");
		System.out.println(miCanal.getDescripcion());

	}

	/**
	 * Pregunta si el usuario quiere volver a crear un item.
	 * 
	 * @return boolean
	 */
	public boolean volverACrearItem() {

		boolean CItem = false;
		String item;

		System.out.println("[+] ¿Quieres crear un Item? (S/N) ...");

		item = entrada.next();
		item = item.toLowerCase();

		if (item.equals("si") || item.equals("s")) {
			CItem = true;
		} else {
			CItem = false;
		}

		return CItem;

	}

	/**
	 * Creación de un item.
	 * 
	 * @return Elemento
	 */
	public Elemento crearItem() {

//		Variables
		String titulo;
		String link;
		String descripcion;

//		Datos
		System.out.println("--------------------");
		System.out.print("Introduce el titulo: ");
		titulo = entrada.next();
		System.out.println("--------------------");
		System.out.print("Introduce el link: ");
		link = entrada.next();
		System.out.println("--------------------");
		System.out.print("Introduce la descripcion: ");
		descripcion = entrada.next();
		System.out.println("--------------------");

		return new Elemento(titulo, link, descripcion);

	}

	/**
	 * Crea un canal, pidiendo titulo, link y descripción al usuario.
	 * 
	 * @param canal
	 */
	public void crearCanal(Canal canal) {

//		Datos
		System.out.println("--------------------");
		System.out.print("Introduce el titulo: ");
		canal.setTitulo(entrada.next());
		System.out.println("--------------------");
		System.out.print("Introduce el link: ");
		canal.setLink(entrada.next());
		System.out.println("--------------------");
		System.out.print("Introduce la descripcion: ");
		canal.setDescripcion(entrada.next());
		System.out.println("--------------------");

	}

	/**
	 * Muestra las opciones del menú.
	 */
	public void mostrarMenu() {

		System.out.println("----------------------------");
		System.out.println("|          MENÚ RSS        |");
		System.out.println("----------------------------");
		System.out.println();
		System.out.println("1 - Crear canal.");
		System.out.println("2 - Editar datos del canal.");
		System.out.println("3 - Nuevo item.");
		System.out.println("4 - Editar datos de un item.");
		System.out.println("5 - Mostrar datos del canal.");
		System.out.println("6 - Guardar fichero rss.");
		System.out.println("7 - SALIR.");
		System.out.println("\n");

	}

	/**
	 * Pide al usuario la acción del menú que quiere realizar.
	 * 
	 * @return int
	 * @throws MenuException
	 */
	public int eleccion() throws MenuException {

		int accion;

		System.out.println("Introduce acción: ");
		accion = entrada.nextInt();

		if (accion < 1 || accion > 7) {
			throw new MenuException("Opción incorrecta");
		}

		return accion;

	}

	/**
	 * Menu
	 * 
	 * @param accion
	 * @param miCanal
	 * @param miFich
	 * @throws Exception
	 */
	public void tratarMenu(int accion, Canal miCanal, FicheroXML miFich) throws Exception {

//		Variables
		boolean CItem = false;
		Elemento nuevoElemento;
		int indexItem;
		String cambiarNombreArchivo;
		String nombreArchivo;
		boolean nombre;

		switch (accion) {

		case 1:

			if (miCanal.getTitulo() != null) {
				throw new Exception("ERROR: Solo de puede crear un canal.");
			}

			System.out.println("-------------------------------------");
			System.out.println("|          CREACIÓN DE CANAL        |");
			System.out.println("-------------------------------------");
			crearCanal(miCanal);

			do {

				CItem = volverACrearItem();

				if (CItem) {
					nuevoElemento = crearItem();
					miCanal.ListaElementos.add(nuevoElemento);
				}

			} while (CItem);
			break;

		case 2:

			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ningún canal \n\n\n");
			}

			mostrarInfoCanal(miCanal);
			modificarInfoCanal(miCanal);
			break;

		case 3:

			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ningún canal \n\n\n");
			}
			System.out.println("------------------------------------");
			System.out.println("|          CREACIÓN DE ITEM        |");
			System.out.println("------------------------------------");
			miCanal.ListaElementos.add(crearItem());
			break;

		case 4:

			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ningún canal \n\n\n");
			}

			if (miCanal.ListaElementos.size() == 0) {
				throw new Exception("ERROR: No hay creado ningún item \n\n\n");
			}

			System.out.println("---------------------------------------------------");
			System.out.println("|          DATOS ACTUALES DE LOS ELEMENTOS        |");
			System.out.println("---------------------------------------------------");
			System.out.println(miCanal.ListaElementos + "\n");

			System.out
					.println("[+] ¿QUE ELEMENTO QUIERE EDITAR? (Hay " + miCanal.ListaElementos.size() + " elemento/s)");
			indexItem = entrada.nextInt();

			if (indexItem <= 0 || indexItem > miCanal.ListaElementos.size()) {
				throw new Exception("ERROR: Opción incorrecta");
			}

			modificarInfoItem(miCanal.ListaElementos.get((indexItem - 1)));
			break;

		case 5:
			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ningún canal \n\n\n");
			}
			mostrarTodaInfoCanal(miCanal);
			break;

		case 6:
			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ningún canal \n\n\n");
			}

			System.out.println("[+] ¿QUIERES PONERLE UN NOMBRE AL FICHERO? (S/N)");
			cambiarNombreArchivo = entrada.next();
			cambiarNombreArchivo = cambiarNombreArchivo.toLowerCase();

			if (cambiarNombreArchivo.equals("si") || cambiarNombreArchivo.equals("s")) {
				nombre = true;
			} else {
				nombre = false;
			}

			if (nombre) {
				System.out.println("[+] ¿QUE NOMBRE QUIERES PONERLE AL ARCHIVO?");
				nombreArchivo = entrada.next();
				FicheroXML miFicheroXML = new FicheroXML(nombreArchivo + ".xml", ".\\src\\rss\\");
				miFicheroXML.Guardar(miCanal);

				System.out.println("------------------------------------");
				System.out.println("|          CREADO CON ÉXITO        |");
				System.out.println("------------------------------------");

			} else {

				miFich.Guardar(miCanal);
				System.out.println("------------------------------------");
				System.out.println("|          CREADO CON ÉXITO        |");
				System.out.println("------------------------------------");

			}

			break;

		}

	}

}