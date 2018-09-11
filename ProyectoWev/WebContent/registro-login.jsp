<!DOCTYPE html>
<html lang="es">
    <head>
    <!-- Autor: Tomás
         Ultima modificacion: 11/09/2018 -->
    <meta charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet"
	   type="text/css"/>
    <link href="css/styles-css/custom-register.css" rel="stylesheet"
	   type="text/css"/>
    <title>Tienda - Registrarse</title>
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
            <form action="#" method="post" name="singUp" id="singUp">

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
                  <label for="psw">Contraseña</label>
                  <input type="password" class="form-control" id="psw" name="psw" placeholder="*********" required>
              </div>
              
              <div class="form-group">
              	 <label for ="psw">Repetir Contraseña</label>
              	 <input type="password" class="form-control" id="repsw" name="repsw" placeholder="*********" required>
              </div>

                <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-log-in"></span> Registrarse</button>
            </form>
        </div>

        <!-- Pie -->
        <div class="container-footer">
            <a href="index.jsp" class="btn btn-danger btn-default pull-left">
            <span class="glyphicon glyphicon-remove"></span> Cancelar</a>
            <p>¿Ya tienes cuenta? <a href="login.jsp">Ingresar</a></p>
        </div>
    </div>

</body>
</html>
