package dao;
import java.util.ArrayList;
import model.Pregunta;

public interface DAOPregunta {
	public ArrayList<Pregunta> select (); // Select all (Obtener todos los objetos)
	public ArrayList<Pregunta> select (String contenido_pregunta); // Select (pregunta especifica
	public Pregunta select (int id); // Select por id
	public int insert (Pregunta pregunta);
	public int update (Pregunta pregunta);
	public boolean delete (Pregunta pregunta);
}
