<!DOCTYPE html>
<html lang="es">
    <head>
    <!-- Autor: Tomás
         Ultima modificacion: 11/09/2018 -->
    <meta charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet"
	   type="text/css"/>
    <link href="css/styles-css/custom-login.css" rel="stylesheet"
	   type="text/css"/>
    <title>Libreria - Login</title>
</head>
<body>

<!-- Login content -->
    <div class="container">

        <!-- Cabecera -->
        <div class="container-header">
            <h1>Login</h1>
        </div>

        <!-- Cuerpo -->
        <div class="container-body">
            <form action="SingIn" method="post">
                <div class="form-group">
                    <label for="username">Usuario</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="gabe" required/>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="********" required>
                </div>
                <button type="submit" class="btn btn-info align" style="align: right;">
                <span class="glyphicon glyphicon-log-in"></span> Ingresar</button>
            </form>
        </div>

        <!-- Pie -->
        <div class="container-footer">
            <a href="index.php" class="btn btn-danger btn-default pull-left">
            <span class="glyphicon glyphicon-remove"></span> Cancelar</a>
            <p>¿No tienes cuenta? <a href="registro-login.html">Registrarse</a></p>

        </div>
    </div>
</body>
</html>
