<%@page import="Main.Entidades.*" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
	<head>
		<style type="text/css">
		    	<%@include file="css/styles-css/custom-index.css" %>
		    	<%@include file="css/bootstrap.min.css"%>
		    	<%@include file="css/shop-homepage.css"%>
		    	<%@include file="css/edit-style.css"%>
	    </style>
	</head>
    <body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Index">Menu principal</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="contact.php">Contacto</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="carro/checkout.php"><span class="glyphicon glyphicon-shopping-cart"></span> Ir al carro</a></li>

					<%
						if(request.getSession().getAttribute("user") != null){
							Usuario usu = (Usuario)request.getSession().getAttribute("user");
							%> 
                    			<li><a href="#?user=<%=usu.getUsuario() %>"><span class="glyphicon glyphicon-shopping-cart"></span> Ver entregas</a></li>
								<li><a href="LogOut"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesión</a></li>
							<%
						}
						else
						{
							%>
								<li><a href="registro-login.jsp">Registrarse</a></li>
	                            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Iniciar sesión</a></li>
							<%
						}
					%>
                </ul>
            </div>
        </div>
    </nav>
    <!-- nav -->

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-md-3">
            	<p class="lead">Buscar libro</p>
            	<div class="panel-group">
       				<form action="BuscarLibro" method="post">
               			<input type="text" class="form-control" name="busca" id="busca" placeholder="Buscar..." />
               		</form>
           		</div>
            	
                <p class="lead">Categorias</p>

                <div class="panel-group">
                  <div class="panel panel-default">
                     <ul class="list-group">
	                     <%
	                     	if(request.getAttribute("listaCategoria") != null){
	                     		ArrayList<Categoria> categorias = (ArrayList<Categoria>)request.getAttribute("listaCategoria");
	                     		for(Categoria cat : categorias){
	                     			%>
	                     			<div div class="panel-heading">
	                     				<h4 class="panel-title"><a href="Index?idCat=<%=cat.getIdCategoria() %>" ><%=cat.getNombre() %></a></h4>
                     				</div>
	                     			<%
	                     		}
	                     		
	                     	}else{
	                     		request.getRequestDispatcher("Index").forward(request, response);
	                     	}
	                     %>
                     </ul>
                   </div>
                 </div>
          </div>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <h1 align="center">Ultimo libros publicados</h1>
                        <%
                        	if(request.getAttribute("listaLibros") != null){
                        		ArrayList<Libro> libros = (ArrayList<Libro>)request.getAttribute("listaLibros");
                        		for(Libro lib : libros){
                        			%>
                        				<h2><a href=#?isbn=<%=lib.getISBN() %>><%=lib.getTitulo() %></a></h2>
                        				<img style="float:left; margin:10px;" alt="" src="./img/<%=lib.getImagen() %>">
                        				
                        				<p>Autor: <%=lib.getAutor() %></p>
                        				<p>Descripcion: <%=lib.getDescripcion() %></p>
                        				<p>Editorial: <%=lib.getEdicion() %></p>
                        				<p>Categoria: <%=lib.getCategoria().getNombre() %>
                        				<h4>Precio: <%=lib.getPrecio() %></h4>
                        				
                        				<%
                        					if(request.getSession().getAttribute("user") != null){
                        						%>
	                        						<form class="form-inline" action="AgregarAlCarrito" method="post">
		                        						<div>
	                        									<input class="form-control" type="number" name="cantidad" id="cantidad" placeholder="cantidad"/>
	                        									<button class="form-control" type="submit">Agregar al carrito</button>
		                        						</div>
	                        						</form>
                        						<%
                        					}
                        				%> 
                        			<%
                        		}
                        	}
                        %>
                    </div>

                </div>
            </div>

        </div>

    </div>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
