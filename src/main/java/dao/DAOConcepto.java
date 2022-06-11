package dao;
import java.util.ArrayList;
import model.Concepto;


public interface DAOConcepto {
	public ArrayList<Concepto> select (); // Select all (Obtener todos los objetos)
	public ArrayList<Concepto> select (String contenido_concepto); // Select (concepto especifica
	public Concepto select (int id); // Select por id
	public int insert (Concepto concepto);
	public int update (Concepto concepto);
	public boolean delete (Concepto concepto);
}
