<%@page import="Main.Negocio.CategoriaLogic"%>
<%@page import="Main.Entidades.Categoria" %>
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
                        <li role="presentation"><a href="ListaMasVendidos">Lista de Libros mas vendidos</a></li>
                        <li role="presentation"><a href="ListaEntregas">Lista de entregas pendientes</a></li>
                    </ul>
                </div>

                <div class="col-md-7 col-md-offset-1">
                    <h1>Listado de categorias</h1>
                    <%
	                	if(request.getAttribute("altaCategoria") != null)
	                	{
	                		%>
	                		<p>Se ha dado de alta la categoria</p>
	                		<%
	               		} 
	                    if(request.getAttribute("bajaCategoria") != null)
	                	{
	                		%>
	                		<p>Se ha dado de baja la categoria</p>
	                		<%
	               		} 
	                    if(request.getAttribute("modifCategoria") != null)
	                	{
	                		%>
	                		<p>Se ha dado de modificado la categoria</p>
	                		<%
	               		} 
	                	if(request.getAttribute("error") != null)
	                	{
	                		%>
	                		<p><%=request.getAttribute("error") %></p>
	                		<%
	               		} 
              			%>
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

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
