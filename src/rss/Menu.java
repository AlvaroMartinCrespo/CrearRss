package rss;

import java.util.*;

/**
 * 
 * @author Alvaro
 *
 */
public class Menu {

	/**
	 * Constructor
	 */
	public Menu() {
		super();
	}

	/**
	 * Muestra titulo, link y descripci�n de canal y titulo, link y descripci�n de
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
	 * Modifica la informaci�n de titulo, link y descripci�n de un elemento.
	 * 
	 * @param elemento
	 */
	public void modificarInfoItem(Elemento elemento) {

		Scanner entrada = new Scanner(System.in);
		
		try {

			System.out.print("[+] Introduce el titulo que quieres poner: ");
			elemento.setTitulo(entrada.nextLine());
			System.out.print("[+] Introduce el link que quieres poner: ");
			elemento.setLink(entrada.nextLine());
			System.out.print("[+] Introduce la descripcion que quieres poner: ");
			elemento.setDescripcion(entrada.nextLine());

		} catch (Exception e) {
			System.out.println("ERROR: Debe de introducir cadenas de tipo String");
		}

	}

	/**
	 * Muestra titulo, link y descripci�n de un elemento.
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
		System.out.println("-DESCRIPCI�N-");
		System.out.println(elemento.getDescripcion());

	}

	/**
	 * Modifica la informaci�n de titulo, link y descripci�n del canal.
	 * 
	 * @param miCanal
	 */
	public void modificarInfoCanal(Canal miCanal) {

		Scanner entrada = new Scanner(System.in);

		System.out.print("[+] Introduce el titulo que quieres poner: ");
		miCanal.setTitulo(entrada.nextLine());
		System.out.print("[+] Introduce el link que quieres poner: ");
		miCanal.setLink(entrada.nextLine());
		System.out.print("[+] Introduce la descripcion que quieres poner: ");
		miCanal.setDescripcion(entrada.nextLine());

	}

	/**
	 * Muestra titulo, link y descripci�n del canal.
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
		System.out.println("-DESCRIPCI�N-");
		System.out.println(miCanal.getDescripcion());

	}

	/**
	 * Pregunta si el usuario quiere volver a crear un item.
	 * 
	 * @return boolean
	 */
	public boolean volverACrearItem() {

		Scanner entrada = new Scanner(System.in);

		boolean CItem = false;
		String item;

		System.out.println("[+] �Quieres crear un Item? (S/N) ...");

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
	 * Creaci�n de un item.
	 * 
	 * @return Elemento
	 */
	public Elemento crearItem() {

		Scanner entrada = new Scanner(System.in);

//		Variables
		String titulo;
		String link;
		String descripcion;

//		Datos
		System.out.println("--------------------");
		System.out.print("Introduce el titulo: ");
		titulo = entrada.nextLine();
		System.out.println("--------------------");
		System.out.print("Introduce el link: ");
		link = entrada.nextLine();
		System.out.println("--------------------");
		System.out.print("Introduce la descripcion: ");
		descripcion = entrada.nextLine();
		System.out.println("--------------------");

		return new Elemento(titulo, link, descripcion);

	}

	/**
	 * Crea un canal, pidiendo titulo, link y descripci�n al usuario.
	 * 
	 * @param canal
	 */
	public void crearCanal(Canal canal) {

		Scanner entrada = new Scanner(System.in);

//		Datos
		try {

			System.out.println("--------------------");
			System.out.print("Introduce el titulo: ");
			canal.setTitulo(entrada.nextLine());
			System.out.println("--------------------");
			System.out.print("Introduce el link: ");
			canal.setLink(entrada.nextLine());
			System.out.println("--------------------");
			System.out.print("Introduce la descripcion: ");
			canal.setDescripcion(entrada.nextLine());
			System.out.println("--------------------");

		} catch (Exception e) {
			System.out.println("ERROR: Debes meter una cadena de caracteres");
		}

	}

	/**
	 * Muestra las opciones del men�.
	 */
	public void mostrarMenu() {

		System.out.println("----------------------------");
		System.out.println("|          MEN� RSS        |");
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
	 * Pide al usuario la acci�n del men� que quiere realizar.
	 * 
	 * @return int
	 * @throws MenuException
	 */
	public int eleccion() throws MenuException {

		int accion;
		Scanner entrada = new Scanner(System.in);

		System.out.println("Introduce acci�n: ");
		accion = entrada.nextInt();

		if (accion < 1 || accion > 7) {
			throw new MenuException("Opci�n incorrecta");
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

		Scanner entrada = new Scanner(System.in);

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
			System.out.println("|          CREACI�N DE CANAL        |");
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
				throw new Exception("ERROR: No hay creado ning�n canal \n\n\n");
			}

			mostrarInfoCanal(miCanal);
			modificarInfoCanal(miCanal);
			break;

		case 3:

			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ning�n canal \n\n\n");
			}
			System.out.println("------------------------------------");
			System.out.println("|          CREACI�N DE ITEM        |");
			System.out.println("------------------------------------");
			miCanal.ListaElementos.add(crearItem());
			break;

		case 4:

			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ning�n canal \n\n\n");
			}

			if (miCanal.ListaElementos.size() == 0) {
				throw new Exception("ERROR: No hay creado ning�n item \n\n\n");
			}

			System.out.println("---------------------------------------------------");
			System.out.println("|          DATOS ACTUALES DE LOS ELEMENTOS        |");
			System.out.println("---------------------------------------------------");
			System.out.println(miCanal.ListaElementos + "\n");

			System.out
					.println("[+] �QUE ELEMENTO QUIERE EDITAR? (Hay " + miCanal.ListaElementos.size() + " elemento/s)");
			indexItem = entrada.nextInt();

			if (indexItem <= 0 || indexItem > miCanal.ListaElementos.size()) {
				throw new Exception("ERROR: Opci�n incorrecta");
			}

			modificarInfoItem(miCanal.ListaElementos.get((indexItem - 1)));
			break;

		case 5:
			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ning�n canal \n\n\n");
			}
			mostrarTodaInfoCanal(miCanal);
			break;

		case 6:
			if (miCanal.getTitulo() == null) {
				throw new Exception("ERROR: No hay creado ning�n canal \n\n\n");
			}

			System.out.println("[+] �QUIERES PONERLE UN NOMBRE AL FICHERO? (S/N)");
			cambiarNombreArchivo = entrada.next();
			cambiarNombreArchivo = cambiarNombreArchivo.toLowerCase();

			if (cambiarNombreArchivo.equals("si") || cambiarNombreArchivo.equals("s")) {
				nombre = true;
			} else {
				nombre = false;
			}

			if (nombre) {
				System.out.println("[+] �QUE NOMBRE QUIERES PONERLE AL ARCHIVO?");
				nombreArchivo = entrada.next();
				FicheroXML miFicheroXML = new FicheroXML(nombreArchivo + ".xml", ".\\src\\rss\\");
				miFicheroXML.Guardar(miCanal);

				System.out.println("------------------------------------");
				System.out.println("|          CREADO CON �XITO        |");
				System.out.println("------------------------------------");

			} else {

				miFich.Guardar(miCanal);
				System.out.println("------------------------------------");
				System.out.println("|          CREADO CON �XITO        |");
				System.out.println("------------------------------------");

			}

			break;

		}

	}

}
