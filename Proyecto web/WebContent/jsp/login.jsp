<!DOCTYPE html>
<html lang="es">
<head>
    <title>Formulario</title>
    <meta charset="utf-8">
    <link type="text/css" href="./../css/style.css" rel="stylesheet" />
</head>
 
<body>
 
    <div id="registrar">
<a>Registrarse</a>
</div> <!-- fin opcion-->
 
    <div id="envoltura">
        <div id="contenedor">
 
            <div id="cabecera" > Login del usuario</div>
 
            <div id="cuerpo">
                <form action="loginUsu" method="post" >
                    <p><label >Usuario:</label></p>
                        <p><input name="usuario" type="text" id="usuario" placeholder="Ingresa Usuario"></p>
 
                    <p><label>Contrase�a:</label></p>
                        <p><input name="contrasenia" type="password" id="contrasenia" placeholder="Ingresa Password" ></p>
 
                    <p id="bot"><input type="submit" id="submit" name="submit" value="Ingresar" class="boton"></p>
                </form>
            </div>
 
            <div id="pie"><a href = "registrar.jsp" >Resgistrarse</a>
            <a href ="#">Olvide mi contrase�a</a>
            </div>
        </div><!-- fin contenedor -->
    </div><!--fin envoltura-->
</body>
 
</html>