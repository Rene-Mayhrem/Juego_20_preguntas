package dao;
import java.util.ArrayList;
import model.Respuesta;

public interface DAORespuesta {
	public ArrayList<Respuesta> select (); // Select all (Obtener todos los objetos)
	public ArrayList<Respuesta> select (String contenido_concepto); // Select (concepto especifica
	public Respuesta select (int id); // Select por id
	public boolean insert (Respuesta respuesta);
	public boolean update (Respuesta respuesta);
	public boolean delete (Respuesta respuesta	);
}