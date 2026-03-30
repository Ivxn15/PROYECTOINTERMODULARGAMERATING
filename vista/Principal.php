<?php
session_start();
require_once("../controlador/apiHelper.php");

if(!isset($_SESSION['usuario_id'])){
    header("Location: ../index.php"); exit();
}

$usuario_id = $_SESSION['usuario_id'];
$usuario_nombre = $_SESSION['usuario_nombre'] ?? 'Usuario';


$valoraciones = llamarAPI("http://localhost:8080/tema5maven/rest/usuarios/resenas/todas", "GET") ?? [];
?>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>GAMERATING</title>
<link rel="stylesheet" href="../web.css">
</head>
<body>
    <style>

main.todo {
    margin-top: 100px; 
}
</style>
<header>
    <h1>GAMERATING⭐</h1>
    <nav>
        <span>Bienvenido, <?php echo htmlspecialchars($usuario_nombre); ?>!</span>
        <button onclick="location.href='nuevaValoracion.php'">Añadir valoración</button>
        <button onclick="location.href='eliminarValoracion.php'">Eliminar valoración</button>
        <a href="../controlador/logout.php">Cerrar sesión</a>
    </nav>
</header>

<div id="fondo1" class="fondo"></div>
<div id="fondo2" class="fondo"></div>

<main class="todo">
<?php if(count($valoraciones) === 0): ?>
    <p>No hay valoraciones aún.</p>
<?php else: ?>
    <?php foreach($valoraciones as $v): ?>
    <div class="Portada">
        <div class="info">
            <h2><?php echo htmlspecialchars($v['juego']); ?></h2>
            <p><?php echo htmlspecialchars($v['comentario']); ?></p>
            <div class="valoracion">
                ⭐ <?php echo htmlspecialchars($v['puntuacion']); ?>/10
            </div>
            <div style="font-size:0.8em; color:#555; margin-top:4px;">
                Publicado por <strong><?php echo htmlspecialchars($v['nombre']); ?></strong>
                el <?php echo date("d/m/Y H:i", strtotime($v['fecha'])); ?>
            </div>
        </div>
    </div>
<?php endforeach; ?>
<?php endif; ?>
</main>

<script>

var imagenes = [
  "../Imagenes/astrobot.jpg",
  "../Imagenes/bo6.jpg",
  "../Imagenes/batman1.jpg",
  "../Imagenes/asa.jpeg",
  "../Imagenes/fallout4.jpg",
  "../Imagenes/helldivers2.jpg",
  "../Imagenes/mw3.jpeg",
  "../Imagenes/liesofp.jpg",
  "../Imagenes/pubg.jpg"
];

var i = 0;
var fondo1 = document.getElementById("fondo1");
var fondo2 = document.getElementById("fondo2");
fondo1.style.backgroundImage = "url(" + imagenes[0] + ")";
fondo1.style.opacity = 1;

setInterval(function() {
  i = (i + 1) % imagenes.length;
  if(fondo1.style.opacity == "1") {
    fondo2.style.backgroundImage = "url(" + imagenes[i] + ")";
    fondo2.style.opacity = 1;
    fondo1.style.opacity = 0;
  } else {
    fondo1.style.backgroundImage = "url(" + imagenes[i] + ")";
    fondo1.style.opacity = 1;
    fondo2.style.opacity = 0;
  }
}, 5000);
</script>
</body>
</html>