package model;

public class Pregunta {
	
	//ATRIBUTOS
	
	private int id;
	private String contenido;
	
	// CONSTRUCTORA
	
	public Pregunta (int id, String contenido) {
		this.id = id;
		this.contenido = contenido;
	}
	
	public Pregunta (int id) {
		this.id = id;
	}
	
	public Pregunta (String contenido) {
		this.contenido = contenido;
	}

	public Pregunta () {
		
	}

	public void setId (int id) {
		this.id = id;
	}

	public int getId () {
		return this.id;
	}

	public void setContenido (String contenido) {
		this.contenido = contenido;
	}

	public String getContenido () {
		return this.contenido;
	}
	
}
