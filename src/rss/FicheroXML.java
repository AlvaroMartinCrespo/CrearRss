package rss;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Clase que gestiona la información de un fichero XML.
 * 
 * @author	DCS - 2022.04.05
 */
public class FicheroXML {

	private String nombre;
	private String ruta;
	private Document doc;
	private Element ultimoNodo;
	
	/**
	 * Constructor vacío.
	 */
	public FicheroXML() {
		this("rss.xml", ".\\");
	}
	
	/**
	 * Constructor sobrecargado con un solo parámetro.
	 * 
	 * @param	nombre	La ruta completa del fichero donde vamos a almacenar el documento XML.
	 */
	public FicheroXML(String nombre) {
		this(nombre, ".\\");
	}
	
	/**
	 * Constructor sobrecargado que acepta dos parámetros.
	 * 
	 * @param	nombre	El nombre del fichero donde vamos a almacenar el documento XML.
	 * @param	ruta	La carpeta (ruta absoluta) donde se ubica el fichero.
	 */
	public FicheroXML(String nombre, String ruta) {
		this.nombre = nombre;
		if (ruta.isBlank()) {
			this.ruta = ".\\";
		} 
		else {
			this.ruta = ruta;
		}
		this.ultimoNodo = null;
		
		crearDocXml();
		crearRaizRSS();
	}

	/**
	 * Crear el objeto para manejar el fichero XML (atributo de clase doc).
	 */
	private void crearDocXml() {
	    try {
	    	
	    	//Crear el objeto para manejar el fichero XML...
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        doc = docBuilder.newDocument();
	    } 
	    catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage() == null ? "***ERROR*** crearDocXml()" : pce.getMessage());
	    } 
	}
	
	/**
	 * Crear el elemento raíz del documento XML.
	 */
	private void crearRaizRSS() {
	    try {
	    	ultimoNodo = CrearElemento("rss", null);
	        
	        //Se agrega el atributo "version" y su valor al nodo raíz...
	    	CrearAtributo("version", "2.0", ultimoNodo);
	    } 
	    catch (Exception e) {
			System.out.println(e.getMessage() == null ? "***ERROR*** crearRaizRSS()" : e.getMessage());
	    } 
	}

	
	/**
	 * Crear un elemento en el documento XML (sobrecarga 1).
	 * 
	 * @param	nombre	El nombre del elemento.
	 * @return	El objeto <em><strong>Element</strong></em> creado.
	 */
	public Element CrearElemento(String nombre) {
		return CrearElemento(nombre, null);
	}

	/**
	 * Crear un elemento en el documento XML (sobrecarga 2).
	 * 
	 * @param	nombre	El nombre del elemento.
	 * @param	padre	El objeto <em><strong>Element</strong></em> que será el padre de nuestro nuevo elemento.
	 * @return	El objeto <em><strong>Element</strong></em> creado.
	 */
	public Element CrearElemento(String nombre, Element padre) {
	    try {
	        //Crear el elemento...
	        Element elemento = doc.createElement(nombre);
	        
	        //Vincularlo a su elemento padre...
	        if (padre == null) {        	
	        	if (ultimoNodo == null) {
	        		doc.appendChild(elemento);
	        	}
	        	else {
	        		ultimoNodo.appendChild(elemento);
	        	}
	        }
	        else {
	        	padre.appendChild(elemento);
	        }
	        
	        return elemento;
	    } 
	    catch (Exception e) {
			System.out.println(e.getMessage() == null ? "***ERROR*** CrearElemento()" : e.getMessage());
	    }
	    
	    //Si se produce algún error retorna un valor nulo...
	    return null;
	}
	
	/**
	 * Asignar contenido a un elemento.
	 * 
	 * @param	elemento	El objeto <em><strong>Element</strong></em> al que vamos a asignar un contenido.
	 * @param	contenido	El valor del contenido que recibirá el elemento.
	 * @return	true/false indicando si finalizó de manera correcta o se produjo algún error.
	 */
	public boolean AsignarContenido(Element elemento, String contenido) {
		try {
			elemento.setTextContent(contenido);
			return true;
		}
	    catch (Exception e) {
			System.out.println(e.getMessage() == null ? "***ERROR*** AsignarContenido()" : e.getMessage());
	    }
		
		return false;
	}
	
	/**
	 * Crear un atributo con su contenido y asignarlo a un elemento.
	 * 
	 * @param	nombre		El nombre del atributo.
	 * @param	contenido	El valor del contenido del atributo del elemento.
	 * @param   elemento	Elemento donde se va a crear el atributo.
	 */
	public void CrearAtributo(String nombre, String contenido, Element elemento) {
	    try {      
	        Attr atributo = doc.createAttribute(nombre);
	        atributo.setValue(contenido);
	        
	        elemento.setAttributeNode(atributo);
	    } 
	    catch (Exception e) {
			System.out.println(e.getMessage() == null ? "***ERROR*** CrearAtributo()" : e.getMessage());
	    } 
	}

	/**
	 * Escribir el contenido del XML en un archivo según los atributos de clase 
	 * <em><strong>ruta</strong></em> y <em><strong>nombre</strong></em>.
	 * 
	 * @param	objCanal	Objeto con la información del canal.
	 * @return	true/false indicando si se creó el fichero RSS correctamente.
	 */
	public boolean Guardar(Canal objCanal) {

		try {

			//**********
			//
			//Ejemplo de comienzo de la creación del documento XML según la información
			//de mi objeto Canal...
			//
			Element miCanal = CrearElemento("channel");
			Element miElemento;
			int contador=0;
			
			miElemento = CrearElemento("title", miCanal);
			AsignarContenido(miElemento, objCanal.getTitulo());
			miElemento= CrearElemento("link", miCanal);
			AsignarContenido(miElemento, objCanal.getLink());
			miElemento= CrearElemento("description", miCanal);
			AsignarContenido(miElemento, objCanal.getDescripcion());
			
			do {
				
				Element item = CrearElemento("item", miCanal);
				miElemento = CrearElemento("title", item);
				AsignarContenido(miElemento, objCanal.ListaElementos.get(contador).getTitulo());
				miElemento= CrearElemento("link", item);
				AsignarContenido(miElemento, objCanal.ListaElementos.get(contador).getLink());
				miElemento= CrearElemento("description", item);
				AsignarContenido(miElemento, objCanal.ListaElementos.get(contador).getDescripcion());
				
				contador++;
				
			}while(contador<objCanal.ListaElementos.size());

			//
			//**********
	
			//**********
			//
			//Continuar vosotros la codificación del método para crear toda la 
			//información necesaria del RSS en el fichero XML...
			//
			//También podéis hacer este conjunto de instrucciones en otro método para 
			//actualizar el doc y llamarlo desde este método Guardar()
			//
			//**********
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage() == null ? 
							   "***ERROR*** Guardar() - Al crear los elementos del fichero XML" : e.getMessage());
			return false;
	    }
		
	    try {
	        //Se escribe el contenido del XML en un archivo...
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        
	        DOMSource source = new DOMSource(doc);
	        
	        if (!nombre.toLowerCase().contains(".xml")) {
	        	nombre = nombre + ".xml";
	        }
	        StreamResult result = new StreamResult(new File(ruta + nombre));
	        
	        transformer.transform(source, result);
	    } 
	    catch (TransformerException tfe) {
			System.out.println(tfe.getMessage() == null ? 
					   		   "***ERROR*** Guardar() - Al escribir el contenido del XML en un archivo" : tfe.getMessage());
			return false;
	    }
	    
	    return true;
	}

	/**
	 * Método <em>Get</em> para acceder al contenido del atributo de clase <em><strong>nombre</strong></em>.
	 * 
	 * @return	Cadena de caracteres con valor de <em><strong>nombre</strong></em>.
	 */
	public String GetNombre() {
		return nombre;
	}

	/**
	 * Método <em>Set</em> para establecer el contenido del atributo de clase <em><strong>nombre</strong></em>.
	 * 
	 * @param	nombre	El valor que vamos a asignar a <em><strong>nombre</strong></em>.
	 */
	public void SetNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método <em>Get</em> para acceder al contenido del atributo de clase <em><strong>ruta</strong></em>.
	 * 
	 * @return	Cadena de caracteres con valor de <em><strong>ruta</strong></em>.
	 */
	public String GetRuta() {
		return ruta;
	}

	/**
	 * Método <em>Set</em> para establecer el contenido del atributo de clase <em><strong>ruta</strong></em>.
	 * 
	 * @param	ruta	El valor que vamos a asignar a <em><strong>ruta</strong></em>.
	 */
	public void SetRuta(String ruta) {
		this.ruta = ruta;
	}
	
	/**
	 * Método <em>Get</em> para acceder al contenido del atributo de clase <em><strong>doc</strong></em>.
	 * 
	 * @return	Objeto de tipo Document con el valor de <em><strong>doc</strong></em>.
	 */
	public Document GetDoc() {
		return doc;
	}

	/**
	 * Método <em>Set</em> para establecer el contenido del atributo de clase <em><strong>doc</strong></em>.
	 * 
	 * @param	doc	El valor que vamos a asignar a <em><strong>doc</strong></em>.
	 */
	public void SetRuta(Document doc) {
		this.doc = doc;
	}

	/**
	 * Método <em>Get</em> para acceder al contenido del atributo de clase <em><strong>ultimoNodo</strong></em>.
	 * 
	 * @return	Objeto de tipo Element con el valor de <em><strong>ultimoNodo</strong></em>.
	 */
	public Element GetUltimoNodo() {
		return ultimoNodo;
	}

	/**
	 * Método <em>Set</em> para establecer el contenido del atributo de clase <em><strong>doc</strong></em>.
	 * 
	 * @param	doc	El valor que vamos a asignar a <em><strong>doc</strong></em>.
	 */
	public void SetUltimoNodo(Element ultimoNodo) {
		this.ultimoNodo = ultimoNodo;
	}
}
