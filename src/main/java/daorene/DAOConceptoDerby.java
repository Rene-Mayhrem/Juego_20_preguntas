package daorene;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Concepto;

public class DAOConceptoDerby extends DAOObject{

    public DAOConceptoDerby() {
        super();
    }

    @Override
    public ArrayList<Object> select() {
        query = "SELECT * FROM concepto ORDER BY id";
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
    public ArrayList<Object> select(Object object) {
        Concepto concepto = (Concepto) object; //? Casteo del objeto
        query = "SELECT * FROM concepto WHERE nombre LIKE (LOWER(?)) ORDER BY id";
		ArrayList conceptos = new ArrayList<Concepto>(); //? contenido del query
		try {
			statement = conn.prepareStatement(this.query);
			statement.setString(1, concepto.getContenido()); //? Aplicamos el parametro de busqueda
			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			while(resultset.next()) {
				int id = Integer.parseInt(resultset.getString(1));
				String contenido = resultset.getString(2);
				Concepto concepto_nuevo = new Concepto(id, contenido); //? Objeto creado con valores de la tabla sql
				conceptos.add(concepto_nuevo); //? lista actualizada
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conceptos;
    }

    @Override
    public Object select(int id) {
        query = "SELECT * FROM concepto WHERE id = ?";
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
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return concepto;
    }

    @Override
    public int insert(Object object) {
        Concepto concepto = (Concepto) object; //? Casteo del objeto
        try {
                query = "INSERT INTO concepto (nombre) VALUES (LOWER(?))";
                statement = conn.prepareStatement(query);
                statement.setString(1, concepto.getContenido());
                statement.executeUpdate(); //? Ejecución del query
                conn.close();
                return 1; //? Todo Ok
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 2; //? Error directo con la Base de Datos
        }
    }

    @Override
    public int update(Object object) {
        Concepto concepto = (Concepto) object; //? Casteo del objeto
        try {
            if(!this.verificar_existencia(concepto)){
                query = "UPDATE concepto SET contenido = LOWER(?) WHERE id = ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, concepto.getContenido());
                statement.setInt(2, concepto.getId());
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
        Concepto concepto = (Concepto) object; //? Casteo del objeto
        query = "DELETE FROM concepto WHERE id = ?";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, concepto.getId());
            statement.executeUpdate(); //? Query ejectuado
            conn.close();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public Object selectRandom() {
        query = "SELECT * FROM concepto ORDER BY RANDOM()";
		Concepto concepto = new Concepto();
		try {
			statement = conn.prepareStatement(this.query);

			resultset = statement.executeQuery(); //? ejecuta el query
			//? Almacena datos mientras el query tenga valores
			if(resultset.next()) {
				concepto.setId(resultset.getInt(1));
				concepto.setContenido(resultset.getString(2));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return concepto;
    }

}
