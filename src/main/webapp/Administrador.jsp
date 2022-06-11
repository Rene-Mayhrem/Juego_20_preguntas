<%@page import="model.Pregunta"%>
<%@page import="daorene.DAOConceptoDerby"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="model.Concepto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<% DAOConceptoDerby daoConcepto = new DAOConceptoDerby(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/tabs-administrador.js"></script>
    <script src="scripts/tabs-form-concepto.js"></script>
</head>

<body>
    <nav class="header">
        <h1>Bienvenido Administrador</h1>
    </nav>

    <div class="content">
        <!-- TABS -->
        <div class="div-tab">
        	<a class="tablink" href="Inicio.html">Home</a>
            <button class="tablink" onclick="openAction(event, 'lst-conceptos')">Ver conceptos</button>
            <button class="tablink" onclick="openAction(event, 'lst-preguntas')">Ver preguntas</button>
            <button class="tablink" onclick="openAction(event, 'crear-concepto')">Crear Concepto</button>
            <button class="tablink" onclick="openAction(event, 'crear-pregunta')">Crear Pregunta</button>
        </div>
        <!-- Tab content -->
        <div id="lst-conceptos" class="tab-content">
            <h3>Conceptos</h3>
            <p>Conceptos agregados</p>
            <table class="data-table">
                <tr>
	                <th>Id</th>
	                <th>Conceptos</th>
                </tr>
                <% 
                ArrayList<Concepto> conceptos = (ArrayList<Concepto>) request.getAttribute("conceptos");
                for (Concepto concepto : conceptos) {
                %>
                	<tr>
		               	<td><% out.print(concepto.getId()); %></td>
		               	<td><% out.print(concepto.getContenido()); %></td>
		               	<td><a class="btn btn-primary" href="Gestionador?menu=accion-administrador&accion=eliminar-concepto&id-concepto=<%out.print(concepto.getId());%>" role="button" id="btn-eliminar">Eliminar</a></td>
                    	<td><a class="btn btn-primary" href="#" role="button" id="btn-modificar">Modificar</a></td>
		            </tr>
                <%
                }
                %>
           </table>
        </div>
        <div id="lst-preguntas" class="tab-content">
            <h3>Preguntas</h3>
            <p>Preguntas agregados</p>
            <table class="data-table">
                <tr>
	                <th>Id</th>
	                <th>Preguntas</th>
                </tr>
                <% 
                ArrayList<Pregunta> preguntas = (ArrayList<Pregunta>) request.getAttribute("preguntas");
                for (Pregunta pregunta : preguntas) {
                %>
                	<tr>
		               	<td><% out.print(pregunta.getId()); %></td>
		               	<td><% out.print(pregunta.getContenido()); %></td>
		               	<td><a class="btn btn-primary" href="Gestionador?menu=accion-administrador&accion=eliminar-pregunta&id-pregunta=<%out.print(pregunta.getId());%>" role="button" id="btn-eliminar">Eliminar</a></td>
                    	<td><a class="btn btn-primary" href="#" role="button" id="btn-modificar">Modificar</a></td>
		            </tr>
                <%
                }
                %>
           </table>
        </div>
        <div id="crear-concepto" class="tab-content">
            <h3>Crear Concepto</h3>
            <form class="form-inline" action="Gestionador?menu=accion-administrador&accion=crear-concepto" method="post">
                <label for="concepto">Concepto:</label>
                <input type="text" id="concepto" placeholder="Insertar el concepto" name="contenido-concepto" required>
                <button type="submit">Crear</button>
            </form>
            <table class="data-table">
                <tr>
	                <th>Id</th>
	                <th>Conceptos</th>
                </tr>
                <% 
                conceptos = (ArrayList<Concepto>) request.getAttribute("conceptos");
                for (Concepto concepto : conceptos) {
                %>
                	<tr>
		               	<td><% out.print(concepto.getId()); %></td>
		               	<td><% out.print(concepto.getContenido()); %></td>
		               	<td><a class="btn btn-primary" href="Gestionador?menu=accion-administrador&accion=crear-concepto&id-concepto=<%out.print(concepto.getId());%>" role="button" id="btn-eliminar">Eliminar</a></td>
                    	<td><a class="btn btn-primary" href="#" role="button" id="btn-modificar">Modificar</a></td>
		            </tr>
                <%
                }
                %>

           </table>
        </div>
        <div id="crear-pregunta" class="tab-content">
            <h3>Crear Pregunta</h3>
            <form class="form-inline" action="Gestionador?menu=accion-administrador&accion=crear-pregunta" method="post">
                <label for="pregunta">Pregunta:</label>
                <input type="text" id="pregunta" placeholder="Inserte su pregunta" name="contenido-pregunta" required>
                <button type="submit">Crear</button>
            </form>
            <table class="data-table">
                <tr>
	                <th>Id</th>
	                <th>Preguntas</th>
                </tr>
                <% 
                preguntas = (ArrayList<Pregunta>) request.getAttribute("preguntas");
                for (Pregunta pregunta : preguntas) {
                %>
                	<tr>
		               	<td><% out.print(pregunta.getId()); %></td>
		               	<td><% out.print(pregunta.getContenido()); %></td>
		               	<td><a class="btn btn-primary" href="Gestionador?menu=accion-administrador&accion=eliminar-pregunta&id-pregunta=<%out.print(pregunta.getId());%>" role="button" id="btn-eliminar">Agregar</a></td>
                    	<td><a class="btn btn-primary" href="#" role="button" id="btn-modificar">Modificar</a></td>
		            </tr>
                <%
                }
                %>
           </table>
        </div>
    </div>

    <!-- The Modal -->
    <div class="modal fade" id="crear-concepto">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Crear Concepto</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">
                    <!--<form id="form-concepto" action="#" method="post">-->
                        <!-- Contenido concepto -->
                        <!--<div class="tab">
                            <label for="lb-contenido-concepto">Concepto: </label>
                            <input type="text" name="concepto" id="inpt-concepto" placeholder="Inserte el concepto" oninput="this.className=''" required>
                        </div>-->
                        <!-- escoger preguntas -->
                        <!--<div class="class">
                            <label for="lbl-lista-preguntas">Preguntas:</label>
                            <input type="text" name="concepto" id="inpt-concepto" placeholder="Inserte el concepto" oninput="this.className=''" required>
                        </div>-->
                        <!-- responder preguntas -->
                        <!--<div class="tab">
                            <label for="lbl-responder-preguntas">Preguntas:</label>
                            <input type="text" name="concepto" id="inpt-concepto" placeholder="Inserte el concepto" oninput="this.className=''" required>
                        </div>

                        <div style="overflow:auto;">
                            <div style="float:right;">
                                <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
                                <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
                            </div>
                        </div>-->
                        <!-- Circles which indicates the steps of the form: -->
                        <!--<div style="text-align:center;margin-top:40px;">
                            <span class="step"></span>
                            <span class="step"></span>
                            <span class="step"></span>
                            <span class="step"></span>
                        </div>-->
                    </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    <!-- The Modal -->
    <div class="modal fade" id="crear-pregunta">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Crear Pregunta</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form id="form-modal" action="#" method="post">
                        <label for="lb-pregunta">Pregunta: </label>
                        <input type="text" name="pregunta" id="inpt-pregunta" placeholder="Inserte su Pregunta" required>
                        <input type="submit" value="Crear">
                    </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</body>
</html>