 package daorene;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.xml.sax.EntityResolver;

import model.Concepto;
import model.Respuesta;

public class DAORespuestaDerby extends DAOObject{

    public DAORespuestaDerby() {
        super();
    }

    @Override
    public ArrayList<Object> select() {
        query = "SELECT c.contenido, p.contenido, r.contenido\n"+
        "FROM respuesta r\n"+
        "JOIN pregunta p\n"+
        "ON (r.id_pregunta = p.id)\n"+
        "JOIN concepto c\n"+
        "ON (r.id_concepto = c.id)\n"+
        "ORDER BY (C.contenido)";
        ArrayList respuestas = new ArrayList<Respuesta>();

        try {
			statement = conn.prepareStatement(this.query);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				String contenido_concepto = resultset.getString(1);
                String contenido_pregunta = resultset.getString(2);
                Boolean contenido = resultset.getBoolean(3);
				Respuesta respuesta = new Respuesta (contenido_pregunta, contenido_concepto, contenido); //? Objeto creado con valores de la tabla sql
				respuestas.add(respuesta); //? lista actualizada
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return respuestas;
    }

    @Override
    public ArrayList<Object> select(Object object) {
        query = "SELECT c.id, c.nombre, p.id, p.contenido, r.contenido\r\n"
        		+ "FROM respuesta r\r\n"
        		+ "JOIN pregunta p\r\n"
        		+ "ON (r.id_pregunta = p.id)\r\n"
        		+ "JOIN concepto c\r\n"
        		+ "ON (r.id_concepto = c.id)\r\n"
        		+ "WHERE c.id = ?\r\n"
        		+ "ORDER BY p.id";
        Concepto concepto = (Concepto) object;
        ArrayList respuestas = new ArrayList();
        
        try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, concepto.getId()); //? Aplicamos el parametro de busqueda
            resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				// formato de tabla c.id, c.nombre, p.id, p.nombre, r.respuesta
				Respuesta respuesta = new Respuesta();
				System.out.println("El concepto es "+concepto.getId());
				respuesta.setId_concepto(resultset.getInt(1));
				respuesta.setContenido_concepto(resultset.getString(2));
				respuesta.setId_pregunta(resultset.getInt(3));
				respuesta.setContenido_pregunta(resultset.getString(4));
				respuesta.setRespuesta(resultset.getBoolean(5));
				System.out.println(respuesta.getId_concepto());
				System.out.println(respuesta.getContenido_concepto());
				System.out.println(respuesta.getId_pregunta());
				System.out.println(respuesta.getContenido_pregunta());
				System.out.println(respuesta.isRespuesta());
				respuestas.add(respuesta);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return respuestas;
    }

    @Override
    public Object select(int id) {

        return null;
    }

    @Override
    public int insert(Object object) {
        Respuesta respuesta = (Respuesta) object;
        try {
            if(!this.verificar_existencia(respuesta)) {
                query = "INSERT INTO respuesta VALUES (?, ?, ?)";
                statement = conn.prepareStatement(query);
                statement.setInt(1, respuesta.getId_pregunta());
                statement.setInt(2, respuesta.getId_concepto());
                statement.setBoolean(3, respuesta.isRespuesta());
                statement.executeUpdate(); //? Ejecución del query
                conn.close();
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
    public int update(Object object) {
        Respuesta respuesta = (Respuesta) object;
        try {
            if(!this.verificar_existencia(respuesta)){
                query = "UPDATE respuesta SET contenido = ? WHERE id_concepto = ? AND id_pregunta = ?";
                statement = conn.prepareStatement(query);
                statement.setBoolean(1, respuesta.isRespuesta());
                statement.setInt(2, respuesta.getId_concepto());
                statement.setInt(3, respuesta.getId_pregunta());
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
        Respuesta respuesta = (Respuesta) object;
        query = "DELETE FROM respuesta WHERE id_concepto = ? AND id_pregunta = ? AND contenido = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, respuesta.getId_concepto());
            statement.setInt(2, respuesta.getId_pregunta());
            statement.setBoolean(3, respuesta.isRespuesta());
            statement.executeUpdate(); //? Query ejectuado
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ArrayList<Object> selectRandom() {
        query = "SELECT c.contenido, p.contenido, r.contenido\n"+
        "FROM respuesta r\n"+
        "JOIN pregunta p\n"+
        "ON (r.id_pregunta = p.id)\n"+
        "JOIN concepto c\n"+
        "ON (r.id_concepto = c.id)\n"+
        "ORDER BY RANDOM()";
        ArrayList respuestas = new ArrayList<Respuesta>();

        try {
			statement = conn.prepareStatement(this.query);
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				String contenido_concepto = resultset.getString(1);
                String contenido_pregunta = resultset.getString(2);
                Boolean contenido = resultset.getBoolean(3);
				Respuesta respuesta = new Respuesta (contenido_pregunta, contenido_concepto, contenido); //? Objeto creado con valores de la tabla sql
				respuestas.add(respuesta); //? lista actualizada
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return respuestas;
    }
    
}
