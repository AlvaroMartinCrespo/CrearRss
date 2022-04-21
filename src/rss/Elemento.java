package rss;

import java.util.ArrayList;
/**
 * 
 * @author Alvaro
 *
 */
public class Elemento {

//	Atributos
	private String titulo;
	private String link;
	private String descripcion;

	/**
	 * Constructor
	 * @param titulo
	 * @param link
	 * @param descripcion
	 */
	public Elemento(String titulo, String link, String descripcion) {

		this.titulo = titulo;
		this.link = link;
		this.descripcion = descripcion;
	}

//	Setters y Getters

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

	@Override
	public String toString() {
		return "· Elemento: \n [+] Titulo=" + titulo + "\n [+] Link=" + link + "\n [+] Descripcion=" + descripcion + "\n\n";
	}

}
