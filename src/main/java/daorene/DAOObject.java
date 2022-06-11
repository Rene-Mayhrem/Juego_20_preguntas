package daorene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.Conexion;

public abstract class DAOObject {

    //? ATRIBUTOS
    protected Connection conn;
    protected PreparedStatement statement;
    protected ResultSet resultset;
    protected String query;

    //? CONSTRUCTORA
    public DAOObject () {
        this.conn = conn = new Conexion().getConnection();
    }

    //? METODOS ABSTRACTOS
    public abstract ArrayList<Object> select ();
    public abstract ArrayList<Object> select (Object object);
    public abstract Object select (int id);
    public abstract int insert (Object object);
    public abstract int update (Object object);
    public abstract boolean delete (Object object); 

    //? METODOS HEREDADOS
    protected boolean verificar_existencia (Object object) {
        return this.select(object).isEmpty();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
