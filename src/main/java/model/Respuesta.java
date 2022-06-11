package model;

public class Respuesta {
	
	// ATRIBUTOS
	private int id_pregunta;
	private int id_concepto;
	private String contenido_pregunta;
	private boolean respuesta;
	private String contenido_concepto;
	
	// CONSTRUCTORA
	public Respuesta (int id_pregunta, int id_concepto, boolean respuesta) {
		this.id_pregunta = id_pregunta;
		this.id_concepto = id_concepto;
		this.respuesta = respuesta;
	}

	public Respuesta (String contenido_pregunta, String contenido_concepto, boolean respuesta) {
		this.contenido_pregunta = contenido_pregunta;
		this.contenido_concepto = contenido_concepto;
		this.respuesta = respuesta;
	}
	
	public Respuesta (boolean respuesta) {
		this.respuesta = respuesta;
	}

	public Respuesta () {
		
	}
	
	//METODOS
	
	public int getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(int id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

	public int getId_concepto() {
		return id_concepto;
	}

	public void setId_concepto(int id_concepto) {
		this.id_concepto = id_concepto;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public String getContenido_pregunta() {
		return contenido_pregunta;
	}

	public void setContenido_pregunta(String contenido_pregunta) {
		this.contenido_pregunta = contenido_pregunta;
	}

	public String getContenido_concepto() {
		return contenido_concepto;
	}

	public void setContenido_concepto(String contenido_concepto) {
		this.contenido_concepto = contenido_concepto;
	}

	

}
