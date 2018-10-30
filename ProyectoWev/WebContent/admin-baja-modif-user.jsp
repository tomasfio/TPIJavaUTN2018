<%@page import="Main.Negocio.UsuarioLogic"%>
<%@page import="Main.Entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">
<head>
        <meta charset="utf-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="Entornos" content="StoreWare">

        <title>StoreWare - CP</title>
		<style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/bootstrap.min.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    </style>

    </head>

    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Navegación</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.php">StoreWare</a><a href="#" class="navbar-brand">•</a><a class="navbar-brand" href="index-cp.php">Control Panel</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                      <li><a><span class="glyphicon glyphicon-user"></span><b> </b></a></li>
                        <li><a href="php/logout.php"><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- nav -->

        <!-- body of the main page -->
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido <%//Usuario%></p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="">Listado de Categorias</a>
                    	<li role="presentation"><a href="admin-alta-categoria.jsp">Nueva Categoria</a>
                        <li role="presentation"><a href="">Listado de Libros</a></li>
                        <li role="presentation"><a href="admin-alta-libros.jsp">Nuevo Libro</a></li>
                        <li role="presentation"><a href="">Listado de usuarios</a></li>
                        <li role="presentation" class="active"><a href="admin-alta-user.jsp">Nuevo usuario</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">

                    <?php if(mysqli_num_rows($resultado) > 0 && isset($_POST['update'])) { ?>
                    <% 
                    	ArrayList<Usuario> usus = (ArrayList<Usuario>)request.getAttribute("ListaUsuario");
                    	for(Usuario usu: usus)
                    { %>
                        <h1>Modificación de usuario</h1>
                        <hr>
                        <form class="form-group" action="" method="post">
                            <div class="form-group">
                                <label for="nombre">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id" value="<%usu.getIdUsuario(); %>" disabled>
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" name="nombre" value="<%usu.getNombre(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="precio">Apellido:</label>
                                <input type="text" class="form-control" name="apellido" value="<%usu.getApellido(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">E-Mail:</label>
                                <input type="email" min="0" class="form-control" name="email" value="<%usu.getEmail(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">Usuario:</label>
                                <input type="text" min="0" class="form-control" name="usuario" value="<%usu.getUsuario(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">Contraseña:</label>
                                <input type="text" min="0" class="form-control" name="password" value="<%usu.getContrase�a(); %>" required>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-warning pull-right" name="submit" value="Modificar usuario">
                            </div>
                        </form>
                    <?php } else if(mysqli_num_rows($resultado) > 0 && isset($_POST['delete'])) { ?>
                    <% { %>
                        <h1>Baja de un usuario</h1>
                        <hr>
                        <form class="form-group" action="" method="post">
                            <div class="form-group">
                                <label for="nombre">ID seleccionado:</label>
                                <input type="number" class="form-control" name="id" value="<%usu.getIdUsuario(); %>" disabled>
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" name="nombre" value="<%usu.getNombre(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="precio">Apellido:</label>
                                <input type="text" class="form-control" name="apellido" value="<%usu.getApellido(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">E-Mail:</label>
                                <input type="email" min="0" class="form-control" name="email" value="<%usu.getEmail(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">Usuario:</label>
                                <input type="text" min="0" class="form-control" name="usuario" value="<%usu.getUsuario(); %>" required>
                            </div>
                            <div class="form-group">
                                <label for="stock">Contraseña:</label>
                                <input type="text" min="0" class="form-control" name="password" value="<%usu.getContrase�a(); %>" required>
                            </div>
                        </form>
                    <% } else { %>
                        <h1>Gestión de un producto</h1>
                        <hr>
                        <div class="alert alert-danger">No existe un usuario con el ID ingresado, o ha ocurrido un error en la transacción.</div>
                        <a class="btn btn-primary" href="" role="button">Volver al listado</a>
                    <?php } ?>
                </div>
        </div>

        <!-- end body of main page -->

        <!-- Footer -->
        <hr>
        <footer>
            <div class="row" style="text-align:center">
                <div class="col-lg-12">
                    <p>Copyright &copy; Tienda 2018 - All rights reserved.</p>
                </div>
            </div>
        </footer>
        <!-- end footer -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
