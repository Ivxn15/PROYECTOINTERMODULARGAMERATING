<?php
session_start();
require_once("../controlador/apiHelper.php");

if(!isset($_SESSION['usuario_id'])){
    header("Location: ../index.php"); exit();
}

$usuario_id = $_SESSION['usuario_id'];
$usuario_nombre = $_SESSION['usuario_nombre'] ?? 'Usuario';


$valoraciones = llamarAPI("http://localhost:8080/tema5maven/rest/usuarios/usuarios/$usuario_id/resenas", "GET") ?? [];
?>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Eliminar valoración - Gamerating</title>
<link rel="stylesheet" href="../web.css">
</head>
<body>
<header>
    <h1>GAMERATING⭐</h1>
    <nav>
        <a href="Principal.php">Volver</a>
    </nav>
</header>

<div id="fondo1" class="fondo"></div>
<div id="fondo2" class="fondo"></div>

<main class="contenedorInicio">
    <form class="contenedorCaja" action="../controlador/eliminarValoracion.php" method="POST">
        <h2>Eliminar valoración</h2>
        <?php if(count($valoraciones) === 0): ?>
            <p>No tienes valoraciones para eliminar.</p>
        <?php else: ?>
            <label for="id">Selecciona una valoración</label>
            <select name="id" id="id" required>
                <?php foreach($valoraciones as $v): ?>
                    <option value="<?php echo $v['id']; ?>">
                        <?php echo htmlspecialchars($v['juego']); ?> (⭐ <?php echo $v['puntuacion']; ?>/10)
                    </option>
                <?php endforeach; ?>
            </select>
            <button type="submit">Eliminar valoración</button>
        <?php endif; ?>
    </form>
</main>

<footer>Gamerating 2026</footer>

<script>
var imagenes = [
  "../Imagenes/alien.jpg",
  "../Imagenes/wukong.jpg",
  "../Imagenes/sonic.jpeg",
  "../Imagenes/bo6.jpg",
  "../Imagenes/ban.jpg",
  "../Imagenes/sot.png",
  "../Imagenes/ark2.jpg",
  "../Imagenes/got.jpeg",
  "../Imagenes/asa.jpeg"
];

var i = 0;
var fondo1 = document.getElementById("fondo1");
var fondo2 = document.getElementById("fondo2");

fondo1.style.backgroundImage = "url(" + imagenes[0] + ")";
fondo1.style.opacity = 1;

setInterval(function () {
  i = (i + 1) % imagenes.length;

  if (fondo1.style.opacity == "1") {
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