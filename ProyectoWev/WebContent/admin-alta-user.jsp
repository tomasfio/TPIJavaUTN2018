<!DOCTYPE html>
<html lang="es">
<head>
		<style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/bootstrap.min.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    </style>

    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido </p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategorias">Listado de Categorias</a>
                        <li role="presentation"><a href="">Listado de Libros</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de Usuarios</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Alta de un nuevo usuario</h1>
                    <hr>
                    <form class="form-group" action="altaUsuario" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="nombre" placeholder="Nombre del usuario..." required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="apellido" placeholder="Apellido del usuario..." required>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" name="email" placeholder="Email del usuario..." required>
                        </div>
                        <div class="form-group">
                            <select name="tipo_usu" class="form-control">
                                <option value="1">Administrador</option>
                                <option value="0">Cliente</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="usuario" placeholder="Usuario del usuario..." required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="contrasenia" placeholder="Contraseña del usuario..." required>
                        </div>
                        <div class="form-group">
                            <button type="reset" value="Reset" class="btn btn-default" >Limpiar</button>
                            <input type="submit" class="btn btn-primary pull-right" name="submit" value="Cargar usuario">
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
 </html>
