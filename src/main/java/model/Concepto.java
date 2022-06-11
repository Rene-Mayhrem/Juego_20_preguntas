package model;

public class Concepto {
	
	// ATRIBUTOS
	
	private int id;
	private String contenido;
	
	// CONSTRUCTORA
	
	public Concepto (int id, String contenido) {
		this.id = id;
		this.contenido = contenido;
	}
	
	public Concepto (int id) {
		this.id = id;
	}
	
	public Concepto (String contenido) {
		this.contenido = contenido;
	}

	public Concepto () {
		
	}
	
	// M�TODOS
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
