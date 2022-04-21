package rss;

import java.util.ArrayList;

/**
 * 
 * @author Alvaro
 * 
 *
 */
public class Canal {

//	Atributos
	private String titulo;
	private String link;
	private String descripcion;
	public ArrayList<Elemento> ListaElementos;

	/**
	 * Contructor vacio
	 */

	public Canal() {
		super();
		ListaElementos = new ArrayList<Elemento>();
	}

	/**
	 * Constructor sobrecargado
	 * 
	 * @param titulo
	 * @param link
	 * @param descripcion
	 * @param listaElementos
	 */
	public Canal(String titulo, String link, String descripcion) {
		super();
		this.titulo = titulo;
		this.link = link;
		this.descripcion = descripcion;
		ListaElementos = new ArrayList<Elemento>();
	}

//	Getters y Setters

	/**
	 * 
	 * @return
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * 
	 * @return
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Elemento> getListaElementos() {
		return ListaElementos;
	}

	/**
	 * 
	 * @param listaElementos
	 */
	public void setListaElementos(ArrayList<Elemento> listaElementos) {
		ListaElementos = listaElementos;
	}

}
