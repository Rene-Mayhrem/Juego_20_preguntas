package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pregunta;

public class DAOPreguntaDerby implements DAOPregunta{
	
	//? ATRIBUTOS
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultset;
	private String query;
	
	public DAOPreguntaDerby (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public ArrayList<Pregunta> select() {
		this.query = "SELECT * FROM pregunta ORDER BY id";
		ArrayList preguntas = new ArrayList<Pregunta>(); //? contenido del query

		try {
			statement = conn.prepareStatement(this.query);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Pregunta pregunta = new Pregunta(id, contenido); //? Objeto creado con valores de la tabla sql
				preguntas.add(pregunta); //? lista actualizada
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preguntas;
	}

	@Override
	public ArrayList<Pregunta> select(String contenido_pregunta) {
		this.query = "SELECT * FROM pregunta WHERE contenido LIKE (LOWER(?)) ORDER BY id";
		ArrayList preguntas = new ArrayList<Pregunta>(); //? contenido del query

		try {
			statement = conn.prepareStatement(this.query);
			statement.setString(1, contenido_pregunta);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Pregunta pregunta = new Pregunta(id, contenido); //? Objeto creado con valores de la tabla sql
				preguntas.add(pregunta); //? lista actualizada
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preguntas;
	}

	@Override
	public Pregunta select(int id) {
		this.query = "SELECT * FROM pregunta WHERE contenido LIKE (LOWER(?)) ORDER BY id";
		Pregunta pregunta = null; //? contenido del query

		try {
			statement = conn.prepareStatement(this.query);
			statement.setInt(1, id);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				//? Inicializacion del objeto
				pregunta = new Pregunta (id, resultset.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pregunta;
	}

	@Override
	public int insert(Pregunta pregunta) {
		try {
            if(!this.verificar_existencia(pregunta)) {
                String query = "INSERT INTO pregunta (contenido) VALUES (LOWER(?))";
                statement = conn.prepareStatement(query);
                statement.setString(1, pregunta.getContenido());
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
	public int update(Pregunta pregunta) {
		try {
            if(!this.verificar_existencia(pregunta)){
                String query = "UPDATE pregunta SET contenido = LOWER(?) WHERE id = ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, pregunta.getContenido());
                statement.setInt(2, pregunta.getId());
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
	public boolean delete(Pregunta pregunta) {
		String query = "DELETE FROM pregunta WHERE id = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, pregunta.getId());
            statement.executeUpdate(); //? Query ejectuado
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	private boolean verificar_existencia (Pregunta pregunta) {
		return this.select(pregunta.getContenido()).isEmpty();
	}
}
