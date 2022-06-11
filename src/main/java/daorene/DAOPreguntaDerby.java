package daorene;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pregunta;

public class DAOPreguntaDerby extends DAOObject{

    public DAOPreguntaDerby() {
        super();
    }

    @Override
    public ArrayList<Object> select() {
        query = "SELECT * FROM pregunta ORDER BY id";
		ArrayList preguntas = new ArrayList<Object>(); //? contenido del query

		try {
			statement = conn.prepareStatement(query);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Pregunta pregunta = new Pregunta(id, contenido); //? Objeto creado con valores de la tabla sql
                preguntas.add(pregunta); //? lista actualizada
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preguntas;
    }

    @Override
    public ArrayList<Object> select(Object object) {
        Pregunta pregunta = (Pregunta) object; //? Casteando objeto
        query = "SELECT * FROM pregunta WHERE contenido LIKE (LOWER(?)) ORDER BY id";
		ArrayList preguntas = new ArrayList<Pregunta>(); //? contenido del query

		try {
			statement = conn.prepareStatement(this.query);
			statement.setString(1, pregunta.getContenido());
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Pregunta pregunta_nueva = new Pregunta(id, contenido); //? Objeto creado con valores de la tabla sql
				preguntas.add(pregunta_nueva); //? lista actualizada
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preguntas;
    }

    @Override
    public Object select(int id) {
        query = "SELECT * FROM pregunta WHERE id = ? ORDER BY id";
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pregunta;
    }

    @Override
    public int insert(Object object) {
        Pregunta pregunta = (Pregunta) object; //? Casteando objeto
        try {
                String query = "INSERT INTO pregunta (contenido) VALUES (LOWER(?))";
                statement = conn.prepareStatement(query);
                statement.setString(1, pregunta.getContenido());
                statement.executeUpdate(); //? Ejecución del query
                conn.close();
                return 1; //? Todo OK
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 3; //? Error directo con la Base de Datos
        }
    }

    @Override
    public int update(Object object) {
        Pregunta pregunta = (Pregunta) object; //? Casteando objeto
        try {
            if(!this.verificar_existencia(pregunta)){
                String query = "UPDATE pregunta SET contenido = LOWER(?) WHERE id = ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, pregunta.getContenido());
                statement.setInt(2, pregunta.getId());
                statement.executeUpdate(); //? Ejecutar sentencia
                conn.close();
                return 1; //? Todo OK
            }else{
                return 2; //?No puede ser creado porque ya existe el elemento
            }
        } catch (Exception e) {
            return 3; //? Error directo con la Base de Datos
        }
    }

    @Override
    public boolean delete(Object object) {
        Pregunta pregunta = (Pregunta) object; //? Casteando objeto
        String query = "DELETE FROM pregunta WHERE id = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, pregunta.getId());
            statement.executeUpdate(); //? Query ejectuado
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }

}
