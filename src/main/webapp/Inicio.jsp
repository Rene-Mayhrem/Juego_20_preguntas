<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es-mx">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HOME | Juego 20 Preguntas</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body id="bg-body">
    <form action="Gestionador?menu=Jugador" method="post" id="frm-login">
        <div>
            <label for="title">?Bienvenido!</label><br>
            <input type="text" name="username" id="username" placeholder="Inserte su nombre de usuario" required><br>
            <button type="submit">?Jugar!</button>
        </div>
    </form>
    
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#adminModal" id="btn-admin">Soy un Administrador</button>

    <!-- MODAL CONTENT -->
    <!-- The Modal -->
    <div class="modal fade" id="adminModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Identificate como Administrador</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form id="admin-modal" action="Gestionador?menu=Administrador" method="post">
                        <label for="lb-clave">Clave de administradores: </label>
                        <input type="password" name="clave" id="inpt-clave" placeholder="Inserte la clave de Administrador" required>
                        <input type="submit" value="Autenticarse">
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