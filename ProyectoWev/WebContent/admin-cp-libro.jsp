<%@page import="Main.Entidades.Libro" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
<head>
		<style type="text/css">
	    	<%@include file="css/styles-css/cp-styles.css"%>
	    	<%@include file="css/shop-homepage.css"%>
	    	<%@include file="../../css/pagination.css"%>
    	</style>
    	
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    </head>
    
    <%
    	
    %>
    
    <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	       <div class="container">
	           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	               <ul class="nav navbar-nav navbar-right">
						<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesión</a></li>
	               </ul>
	           </div>
	       </div>
	   </nav>
        <div class="container">
        <div class="row">
                <div class="col-md-3">
                    <p class="lead">Bienvenido </p>
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation"><a href="ListaCategorias">Listado de Categorias</a>
                        <li role="presentation"><a href="ListaLibros">Listado de Libros</a></li>
                        <li role="presentation"><a href="ListaUsuario">Listado de Usuarios</a></li>
                        <li role="presentation"><a href="ListaVenta">Lista de Ventas</a></li>
                        <li role="presentation"><a href="ListaVenta">Lista de Libros mas vendidos</a></li>
                        <li role="presentation"><a href="ListaEntregas">Lista de entregas pendientes</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Listado de libros</h1>
					<hr>
                     <form class="form-inline" action="LibroABM" method="get">
                          <div class="form-group">
                              <a class="btn btn-success" href="FormAltaLibro" role="button">Nuevo libro</a>
                          </div>
                          <div class="form-group pull-right">
                              <input type="number" min='0' class="form-control" name="isbn" id="isbn" placeholder="Ingrese ISBN" required>
                              <button type="submit" class="btn btn-warning" name="btnUpdate" value="update">Modificar</button>
                              <button type="submit" class="btn btn-danger" name="btnDelete" value="delete">Eliminar</button>
                          </div>
                      </form>
                      <br>
                        <% 
                    	if(request.getAttribute("existeLibro") != null)
                    	{
                    		if(!(boolean)request.getAttribute("existeLibro"))
                    		{
                    			%><p>El id ingresado no pertenece a ninguna libro registrado en el sistema</p><%
                    		}
                    	}
                   		%>
                   		<%
			                	if(request.getAttribute("error") != null)
			                	{
			                		%>
			                		<p><%=request.getAttribute("error") %></p>
			                		<%
			               		} 
		               		%>
                      <table class="table table-striped">
                          <thead>
                              <tr>
                                  <td><b>ID</b></td>
                                  <td><b>Nombre</b></td>
                                  <td><b>Descripcion</b></td>
                                  <td><b>Autor</b></td>
                                  <td><b>Fecha</b></td>
                                  <td><b>Edicion</b></td>
                                  <td><b>Precio</b></td>
                                  <td><b>Categoria</b></td>
                              </tr>
                          </thead>
                          <tbody>
                              <%
                              if(request.getAttribute("listaLibros") != null){
                              	ArrayList<Libro> libros = (ArrayList<Libro>)request.getAttribute("listaLibros");
                              	for(Libro lib : libros){
                              %>
                              	<tr>
	                                  <td><%=Integer.toString(lib.getISBN()) %></td>
	                                  <td><%=lib.getTitulo().toString() %></td>
	                                  <td><%=lib.getDescripcion() %></td> 
	                                  <td><%=lib.getAutor() %></td>
	                                  <td><%=lib.getFecha().toString() %></td>
	                                  <td><%=lib.getEdicion().toString() %></td>
	                                  <td><%=Double.toString(lib.getPrecio()) %></td>
	                                  <td><%=lib.getCategoria().getNombre().toString() %></td>
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

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
