<!doctype html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GAMERATING</title>
<link rel="stylesheet" href="web.css">
</head>
<body>
<?php
session_start();
if (isset($_SESSION['error'])) {
    echo "<p style='color:red'>" . $_SESSION['error'] . "</p>";
    unset($_SESSION['error']);
}
?>

<div id="fondo1" class="fondo"></div>
<div id="fondo2" class="fondo"></div>

<main class="contenedorInicio">
    <form class="contenedorCaja" action="controlador/inicioControlador.php" method="POST">
    <img src="Imagenes/GameratingLogo.png" alt="Logo Gamerating">
    <h1>Iniciar sesión</h1>
    
    <label for="login">Correo electrónico</label>
    <input type="email" name="login" id="login" required>
    
    <label for="password">Contraseña</label>
    <input type="password" name="password" id="password" required>
    
    <button type="submit">Entrar</button>
    <p class="registro">¿Es tu primera vez? <a href="vista/Registro.php">Regístrate</a></p>
  </form>
</main>

<script>
var imagenes = [
  "Imagenes/rdr2.png","Imagenes/tlou2.jpg","Imagenes/re7.jpg",
  "Imagenes/skyrim.jpg","Imagenes/ern.jpg","Imagenes/link.jpg",
  "Imagenes/cyberpunk2077.png","Imagenes/minecraft.jpg","Imagenes/re8.jpg"
];

var precarga = [];
for(var j=0;j<imagenes.length;j++){ precarga[j]=new Image(); precarga[j].src=imagenes[j]; }

var i=0;
var fondo1=document.getElementById("fondo1");
var fondo2=document.getElementById("fondo2");

fondo1.style.backgroundImage="url("+imagenes[0]+")";
fondo1.style.opacity=1;

setInterval(function(){
    i=(i+1)%imagenes.length;
    if(fondo1.style.opacity=="1"){
        fondo2.style.backgroundImage="url("+imagenes[i]+")";
        fondo2.style.opacity=1;
        fondo1.style.opacity=0;
    }else{
        fondo1.style.backgroundImage="url("+imagenes[i]+")";
        fondo1.style.opacity=1;
        fondo2.style.opacity=0;
    }
},5000);
</script>
</body>
</html>