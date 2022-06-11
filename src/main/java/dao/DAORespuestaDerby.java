package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Respuesta;

public class DAORespuestaDerby implements DAORespuesta {
	
	// ATRIBUTOS
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resulset;
	private String sql;
	
	public DAORespuestaDerby (Connection conn) {
		this.conn = conn;
	}

	@Override
	public ArrayList<Respuesta> select() {
		
		return null;
	}

	@Override
	public ArrayList<Respuesta> select(String contenido_concepto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Respuesta select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Respuesta respuesta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Respuesta respuesta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Respuesta respuesta) {
		// TODO Auto-generated method stub
		return false;
	}

}
