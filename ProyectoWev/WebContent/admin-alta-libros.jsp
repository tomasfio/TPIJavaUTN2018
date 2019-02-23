<%@page import="Main.Entidades.Categoria" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>

   	<style type="text/css">
    	<%@include file="css/bootstrap.min.css"%>
    	<%@include file="css/styles-css/cp-styles.css"%>
    	<%@include file="css/shop-homepage.css"%>
    </style>
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
                    <p class="lead">Bienvenido</p>
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
                    <h1>Alta de un nuevo libro</h1> <hr>

                    <form class="form-group" action="AltaLibro" enctype="multipart/form-data" method="get">
                    	<div class="form-group">
                    		<input type="number" class="form-control" name="ISBN" placeholder="ISBN del libro..." required>
                    	</div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="titulo" placeholder="Titulo del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="text" class="form-control" name="descripcion" placeholder="Descripcion del libro... " required>
                        </div>
                        <div class="form-group">
                        	<input type="text" class="form-control" name="autor" placeholder="Autor del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="date" class="form-control" name="fecha" placeholder="Fecha de publicacion del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="text" class="form-control" name="edicion" placeholder="Edicion del libro..." required>
                        </div>
                        <div class="form-group">
                        	<input type="number" step="any" min="0" class="form-control" name="precio" placeholder="Precio del libro..." required>
                        </div>
                        <div class="form-group">
                            <select name="categoria" class="form-control">
                           	<%
                           	  if(request.getAttribute("ListaCategoria")!=null){
                				ArrayList<Categoria> cats = (ArrayList<Categoria>)request.getAttribute("ListaCategoria");
                				for(Categoria cat: cats){%>
                                <option value="<%=cat.getIdCategoria()%>"><%=cat.getNombre().toString() %></option>
                             <%	
                             	}
                			  }
                           	  else{
                           		  request.getRequestDispatcher("FormAltaLibro").forward(request,response);
                           	  }
                			 %>
                            </select>
                        </div>
                        <div class="form-group">
                        	<input type="file" name="imagen" />
                        </div>
                        <div class="form-group">
                            <button type="reset" value="Reset" class="btn btn-default" >Limpiar</button>
                            <input type="submit" class="btn btn-primary pull-right" name="submit" value="Cargar producto">
                        </div>
                        <%
                        	if(request.getAttribute("registroLibro") != null){
                        		if((boolean)request.getAttribute("registroLibro")){
                        			%><p>El libro a sido dado de alta</p><%
                        		}
                        		else{
                        			%><p>El libro no ha podido ser dado de alta</p><%
                        		}
                        	}
                        %>
                        
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
