<%@page import="Main.Negocio.CategoriaLogic"%>
<%@page import="Main.Entidades.Categoria" %>
<%@page import="java.util.Collection" %>
<!DOCTYPE html>
<html lang="es">
<head>
        <meta charset="utf-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="Java" content="Libreria">

        <title>Tienda - CP</title>

        <link href="css/styles-css/cp-styles.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">

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
                    <a class="navbar-brand" href="index.jsp">Libreria</a><a href="#" class="navbar-brand">•</a><a class="navbar-brand" href="#">Control Panel</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                      <li><a><span class="glyphicon glyphicon-user"></span><b></b></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- nav -->

        <!-- body of the main page -->
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido</p>
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
                    <h1>Alta de un nuevo libro</h1> <hr>

                    <form class="form-group" action="#" method="post">
                    	<div class="form-group">
                    		<input type="number" class="form-control" name="ISBN" placeholder="ISBN del libro..." required>
                    	</div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="titulo" placeholder="Nombre del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="text" class="form-control" name="descripcion" placeholder="Descripcion del libro... " required>
                        </div>
                        <div class="form-group">
                        	<input type="text" class="form-control" name="autor" placeholder="Titulo del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="date" class="form-control" name="fecha" placeholder="Fecha de publicacion del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="text" class="form-control" name="edicion" placeholder="Edicion del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="number" min="0" class="form-control" name="precio" placeholder="Precio del libro..." required>
                        </div>
                        <div class="form-group">
                            <select name="categoria" class="form-control">
                           	<%
                           		CategoriaLogic cl = new CategoriaLogic();
                				Collection<Categoria> cats = cl.GetAll();
                				for(Categoria cat: cats){%>
                                <option><%cat.getNombre().toString(); %></option><%}%>
                            </select>
                        </div>
                        <div class="form-group">
                            <button type="reset" value="Reset" class="btn btn-default" >Limpiar</button>
                            <input type="submit" class="btn btn-primary pull-right" name="submit" value="Cargar producto">
                        </div>
                        
                    </form>
                </div>
            </div>
        </div>

        <!-- end body of main page -->

        <!-- Footer -->
        <hr>
        <footer>
            <div class="row" style="text-align:center">
                <div class="col-lg-12">
                    <p>Copyright &copy; Libreria 2018 - All rights reserved.</p>
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
