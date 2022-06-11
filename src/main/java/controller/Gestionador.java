package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Concepto;
import model.Pregunta;
import model.Respuesta;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import connection.Conexion;
import dao.DAOConcepto;
import daorene.DAOConceptoDerby;
import daorene.DAOPreguntaDerby;
import daorene.DAORespuestaDerby;

/**
 * Servlet implementation class Gestionador 
 * @author René Cruz
 */
public class Gestionador extends HttpServlet {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private DataSource ds;
	private Concepto concepto;
	private Pregunta pregunta;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gestionador() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//? Generar la conexion a la base de datos usando el pool de conexiones de tomcat
		super.init();
		InitialContext cxt;

		try {
			cxt = new InitialContext();
			if (cxt != null) {
				this.ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/ds"); // Especificado en el XML de tomcat
				if (this.ds == null) {
					throw new ServletException("No se encontro el Data Source");
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//? Obtener el parametro de entrada
		String menu = request.getParameter("menu");
		switch(menu) {
			// Elegir un caso en especifico
			case "Administrador":
				String clave = request.getParameter("clave");
				if(clave.equals("admin123") || clave.equals("hola123")) { //? Claves fijas de administrados
					ejecutarAdministrador(request, response);
				} else {
					request.setAttribute("isError", (boolean) true);
					request.getRequestDispatcher("Inicio.html").forward(request, response);
				}
				break;
			case "Jugador":
				boolean cont = true;
				// Se crear un concepto que tenga 20 preguntas registradas o más
				while(cont) {
					DAOConceptoDerby daoConcepto = new DAOConceptoDerby();
					DAORespuestaDerby daoRespuesta = new DAORespuestaDerby();
					Concepto concepto = (Concepto) daoConcepto.selectRandom();
					ArrayList respuestas_obj = daoRespuesta.select(concepto);
					System.out.println("Tamaño: "+respuestas_obj.size());
					if(respuestas_obj.size() >= 20) {
						for (Object obj : respuestas_obj) {
							Respuesta respuesta = (Respuesta) obj;
						}
						cont = false;
						request.setAttribute("respuestas", respuestas_obj);
					}
				}
				
				request.getRequestDispatcher("Juego.jsp").forward(request, response);
				break;
			case "accion-administrador":
				accionesAdministrador(request, response);
				break;
			case "accion-jugador":
				accionesJugador(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void ejecutarAdministrador (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDatos(request, response);
	}
	
	private void accionesJugador (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respuesta_concepto = request.getParameter("respuesta").toLowerCase();
		String concepto_final = request.getParameter("concepto_final").toLowerCase();
		System.out.print( respuesta_concepto+" VS "+concepto_final);
		if(concepto_final.equals(respuesta_concepto)) {
			request.getRequestDispatcher("Ganador.html").forward(request, response);
		} else {
			request.getRequestDispatcher("Perdedor.html").forward(request, response);
		}
	}
	
	private void accionesAdministrador (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		DAOConceptoDerby daoConcepto = new DAOConceptoDerby();
		DAOPreguntaDerby daoPregunta = new DAOPreguntaDerby();
		int id = 0;
		switch(accion) {
		case "eliminar-concepto":
			id = Integer.parseInt(request.getParameter("id-concepto"));
			concepto = (Concepto) daoConcepto.select(id);
			daoConcepto.setConn(new Conexion().getConnection());
			daoConcepto.delete(concepto);
			break;
		case "eliminar-pregunta":
			id = Integer.parseInt(request.getParameter("id-pregunta"));
			pregunta = (Pregunta) daoPregunta.select(id);
			daoPregunta.setConn(new Conexion().getConnection());
			daoPregunta.delete(pregunta);
			break;
		case "crear-concepto":
			concepto = new Concepto();
			concepto.setContenido(request.getParameter("contenido-concepto"));
			daoConcepto.setConn(new Conexion().getConnection());
			daoConcepto.insert(concepto);
			break;
		case "crear-pregunta":
			pregunta = new Pregunta();
			pregunta.setContenido(request.getParameter("contenido-pregunta"));
			daoPregunta.setConn(new Conexion().getConnection());
			daoPregunta.insert(pregunta);
			break;
		}
		getDatos(request, response);
		
	}
	
	private void ejecutarJugador (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Juego.jsp").forward(request, response);
	}
	
	private void getDatos (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOConceptoDerby daoConcepto = new DAOConceptoDerby();
		DAOPreguntaDerby daoPregunta = new DAOPreguntaDerby();
		// Mostrar objetos de conceptos en Administrador
		ArrayList obj_conceptos = daoConcepto.select();
		ArrayList<Concepto> conceptos = new ArrayList<Concepto>();
		for (Object obj : obj_conceptos) {
			conceptos.add((Concepto) obj); // Preparar conceptos
		}
		request.setAttribute("conceptos", conceptos);
		// Mostrar objetos de preguntas en Administrador
		ArrayList obj_preguntas = daoPregunta.select();
		ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
		for (Object obj : obj_preguntas) {
			preguntas.add((Pregunta) obj); // Preparar preguntas	
		}		
		request.setAttribute("preguntas", preguntas);
		request.getRequestDispatcher("Administrador.jsp").forward(request, response);
	}
}
