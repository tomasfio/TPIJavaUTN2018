<!DOCTYPE html>
<html lang="es">
    <meta charset="utf-8">
    <style type="text/css">
    	<%@include file="css/bootstrap.min.css"%>
    	<%@include file="css/styles-css/custom-login.css"%>
    </style>
    <title>Login</title>
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
                    <input type="text" class="form-control" id="usuario" name="usuario" required/>
                </div>
                <div class="form-group">
                    <label for="password">Contrase�a</label>
                    <input type="password" class="form-control" id="pass" name="pass" required>
                </div>
                <button type="submit" class="btn btn-info align" style="align: right;">
                <span class="glyphicon glyphicon-log-in"></span> Ingresar</button>
            </form>
            <%
        	if(request.getAttribute("autentificacion") != null){
        		if((boolean)request.getAttribute("autentificacion") == false){
        			%>
	        			<div class="form-group">
	        				<label>El usuario y/o contrase�a son incorrectos</label>
	        			</div>
        			<%
        			}
	        	}
	        %>
        </div>
     
        <!-- Pie -->
        <div class="container-footer">
            <a href="Index" class="btn btn-danger btn-default pull-left">
            <span class="glyphicon glyphicon-remove"></span> Cancelar</a>
            <p>�No tienes cuenta? <a href="registro-login.jsp">Registrarse</a></p>

        </div>
    </div>
</body>
</html>
