package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListDataEvent;

import model.Concepto;

public class DAOConceptoDerby implements DAOConcepto{
	
	//ATRIBUTOS
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultset; 
	private String query;
	
	public DAOConceptoDerby (Connection conn) {
		this.conn = conn;
	}

	@Override
	public ArrayList<Concepto> select() {
		this.query = "SELECT * FROM concepto ORDER BY id";
		ArrayList conceptos = new ArrayList<Concepto>(); //? contenido del query

		try {
			statement = conn.prepareStatement(this.query);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Concepto concepto = new Concepto(id, contenido); //? Objeto creado con valores de la tabla sql
				conceptos.add(concepto); //? lista actualizada
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conceptos;
	}

	@Override
	public ArrayList<Concepto> select(String contenido_concepto) {
		this.query = "SELECT * FROM concepto WHERE nombre LIKE (LOWER(?)) ORDER BY id";
		ArrayList conceptos = new ArrayList<Concepto>(); //? contenido del query
		try {
			statement = conn.prepareStatement(this.query);
			statement.setString(1, contenido_concepto); //? Aplicamos el parametro de busqueda
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Concepto concepto = new Concepto(id, contenido); //? Objeto creado con valores de la tabla sql
				conceptos.add(concepto); //? lista actualizada
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conceptos;
	}

	@Override
	public Concepto select(int id) {
		this.query = "SELECT * FROM concepto WHERE id = ?";
		Concepto concepto = new Concepto();
		try {
			statement = conn.prepareStatement(this.query);
			statement.setInt(1, id); //? Aplicamos el parametro de busqueda
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				concepto.setId(id);
				concepto.setContenido(resultset.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return concepto;
	}

	@Override
	public int insert(Concepto concepto) {
        try {
            if(!this.verificar_existencia(concepto)) {
                String query = "INSERT INTO concepto (contenido) VALUES (LOWER(?))";
                statement = conn.prepareStatement(query);
                statement.setString(1, concepto.getContenido());
                statement.executeUpdate(); //? Ejecución del query
                return 1; //? Todo OK
            } else {
                return 2; //? No puede ser creado porque ya existe ese elemento
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 3; //? Error directo con la Base de Datos
        }
	}

	@Override
	public int update(Concepto concepto) {
        try {
            if(!this.verificar_existencia(concepto)){
                String query = "UPDATE concepto SET contenido = LOWER(?) WHERE id = ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, concepto.getContenido());
                statement.setInt(2, concepto.getId());
                statement.executeUpdate(); //? Ejecutar sentencia
                return 1; //? Todo OK
            }else{
                return 2; //?No puede ser creado porque ya existe el elemento
            }
        } catch (Exception e) {
            return 3; //? Error directo con la Base de Datos
        }
	}

	@Override
	public boolean delete(Concepto concepto) {
        String query = "DELETE FROM concepto WHERE id = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, concepto.getId());
            statement.executeUpdate(); //? Query ejectuado
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	private boolean verificar_existencia (Concepto concepto) {
		return this.select(concepto.getContenido()).isEmpty();
	}
}
