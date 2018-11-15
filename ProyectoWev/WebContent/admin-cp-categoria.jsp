<%@page import="Main.Negocio.CategoriaLogic"%>
<%@page import="Main.Entidades.Categoria" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
<head>
        <meta charset="utf-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="Entornos" content="Libreria">

        <title>Libreria - CP ABM Categoria</title>

		<style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/bootstrap.min.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    	<%@include file="../../css/pagination.css"%>
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
                    <a class="navbar-brand" href="">Libreria</a><a href="#" class="navbar-brand"></a><a class="navbar-brand" href="">Control Panel</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                      <li><a><span class="glyphicon glyphicon-user"></span><b> <%//Usurio %></b></a></li>
                        <li><a href=""><span class="glyphicon glyphicon-log-in"></span> Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- nav -->

        <!-- body of the main page -->
        <div class="container">
        <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido </p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategorias">Listado de Categorias</a>
                    	<li role="presentation"><a href="admin-alta-categoria.jsp">Nueva Categoria</a>
                        <li role="presentation"><a href="">Listado de Libros</a></li>
                        <li role="presentation"><a href="Categoria">Nuevo Libro</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de usuarios</a></li>
                        <li role="presentation" class="active"><a href="admin-alta-user.jsp">Nuevo usuario</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Listado de categorias</h1>
					<hr>
                     <form class="form-inline" action="CategoriaABM" method="get">
                          <div class="form-group">
                              <a class="btn btn-success" href="admin-alta-categoria.jsp" role="button">Nuevo categoria</a>
                          </div>
                          <div class="form-group pull-right">
                              <input type="number" min='0' class="form-control" name="id_categoria" id="id_categoria" placeholder="Ingrese ID" required>
                              <button type="submit" class="btn btn-warning" name="btnUpdate" value="update">Modificar</button>
                              <button type="submit" class="btn btn-danger" name="btnDelete" value="delete">Eliminar</button>
                          </div>
                      </form>
                      <br>
                        <% 
                    	if(request.getAttribute("existeCategoria") != null)
                    	{
                    		if(!(boolean)request.getAttribute("existeCategoria"))
                    		{
                    			%><p>El id ingresado no pertenece a ninguna categoria registrado en el sistema</p><%
                    		}
                    	}
                   		%>
                      <table class="table table-striped">
                          <thead>
                              <tr>
                                  <td><b>ID</b></td>
                                  <td><b>Nombre</b></td>
                                  <td><b>Descripcion</b></td>
                              </tr>
                          </thead>
                          <tbody>
                              <%
                              if(request.getAttribute("ListaCategoria") != null){
                              	ArrayList<Categoria> categorias = (ArrayList<Categoria>)request.getAttribute("ListaCategoria");
                              	for(Categoria cat : categorias){
                              %>
                              	<tr>
	                                  <td><%=Integer.toString(cat.getIdCategoria()) %></td>
	                                  <td><%=cat.getNombre().toString() %></td>
	                                  <td><%=cat.getDescipcion().toString() %></td> 
                              	</tr>
                             <%
                              	}
                              }
                              %>
                        </tbody>
                    </table>
            	</div>
            </div>
        </div>
        <hr>
        <footer>
            <div class="row" style="text-align:center">
                <div class="col-lg-12">
                    <p></p>
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
