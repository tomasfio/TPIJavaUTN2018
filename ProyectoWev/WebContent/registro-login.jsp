<!DOCTYPE html>
<html lang="es">
    <head>
    <!-- Autor: Tom�s
         Ultima modificacion: 11/09/2018 -->
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style type="text/css">
    	<%@include file="css/styles-css/custom-register.css"%>
    </style>
    <title>Libreria - Registrarse</title>
</head>
<body>

<!-- Login content -->
    <div class="container">

        <!-- Cabecera -->
        <div class="container-header">
            <h1>Registrarse</h1>
        </div>


        <!-- Cuerpo -->
        <div class="container-body">
            <form action="SingUp" method="post" name="singUp" id="singUp">

              <div class="form-group">
                  <label for="nombre">Nombre</label>
                  <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Gabe" required>
              </div>

              <div class="form-group">
                  <label for="apellido">Apellido</label>
                  <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Newell" required>
              </div>

              <div class="form-group">
                  <label for="email">e-mail</label>
                  <input type="email" class="form-control" id="email" name="email" placeholder="mimail@midominio.com" required>
              </div>

              <div class="form-group">
                  <label for="usrname">Usuario</label>
                  <input type="text" class="form-control" id="usrname" name="usrname" placeholder="gabe" required>
              </div>

              <div class="form-group">
                  <label for="psw">Contrase�a</label>
                  <input type="password" class="form-control" id="psw" name="psw" placeholder="*********" required>
              </div>

                <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-log-in"></span> Registrarse</button>
            </form>
            <%
                	if(request.getAttribute("error") != null){
                		%>
                		<p><%=request.getAttribute("error") %></p>
                		<%
                		}
               		%>
        </div>

        <!-- Pie -->
        <div class="container-footer">
            <a href="Index" class="btn btn-danger btn-default pull-left">
            <span class="glyphicon glyphicon-remove"></span> Cancelar</a>
            <p>�Ya tienes cuenta? <a href="login.jsp">Ingresar</a></p>
        </div>
    </div>

</body>
</html>
