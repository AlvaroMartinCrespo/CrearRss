package rss;

import java.util.*;

/**
 * Clase principal del programa.
 * 
 * @author DCS - 2022.04.05
 */
public class ProgramaRSS {

	static Scanner entrada = new Scanner(System.in);

	/**
	 * Método principal de la aplicación.
	 */
	public static void main(String[] args) {

//		Variables
		int accion;
		boolean exception = false;

		FicheroXML miFichXML = new FicheroXML("rss.xml", ".\\src\\rss\\");
		Canal miCanal = new Canal();
		Menu menu=new Menu();

		do {

			try {

				do {

					menu.mostrarMenu();
					accion=menu.eleccion();
					menu.tratarMenu(accion, miCanal, miFichXML);
					exception = false;

				} while (accion!=7);

			} catch (MenuException e) {
				System.out.println(e.getMessage());
				exception = true;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				exception = true;
			}

		} while (exception);

		System.out.println("Saliendo...");

	}
}