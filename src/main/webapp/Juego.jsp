<%@page import="model.Respuesta"%>
<%@page import="model.Pregunta"%>
<%@page import="model.Concepto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es-mx">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Juego | 20 Preguntas</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <nav class="header">
        <h1>Bienvenido <% out.print(request.getParameter("username"));%></h1>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="btn-respuesta">Se la respuesta!</button>
        <a href="Inicio.html" id="btn-cancelar">Rendirme!</a>
    </nav>
    <div class="content">
    	<table class="data-table">
        	<tr>
        		<th>No. Pregunta</th>
			    <th>Pregunta</th>
	        </tr>
	        <% 
	        ArrayList<Respuesta> respuestas = (ArrayList<Respuesta>) request.getAttribute("respuestas");
	        int contador = 1;
	        for (Respuesta respuesta : respuestas) {
	        %>
		       	<tr>
		       		<td><% out.print(contador++); %></td>
					<td><% out.print(respuesta.getContenido_pregunta()); %></td>
					<td><% out.print(respuesta.getContenido_concepto()); %></td>
					<td><% out.print(respuesta.isRespuesta()); %></td>
					<td><a class="btn btn-primary" href="Gestionador?menu=accion-administrador&accion=eliminar-concepto&id-concepto=" role="button" id="btn-eliminar">Ver respuesta</a></td>
				</tr>
	        <%
	        }
	        %>
        </table>
        <!-- The Modal -->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Ya se la respuesta!</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button >
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form id="form-modal" action="Gestionador?menu=accion-jugador&concepto_final=<%out.print(respuestas.get(0).getContenido_concepto());%>" method="post">
                            <label for="lb-respuesta">Respuesta: </label>
                            <input type="text" name="respuesta" id="inpt-respuesta" placeholder="Inserte su respuesta" required>
                            <input type="submit" value="Adivinar!">
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>