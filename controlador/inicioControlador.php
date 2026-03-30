<?php
session_start();
require_once("../controlador/apiHelper.php");

$correo = $_POST['login'] ?? '';
$password = $_POST['password'] ?? '';

if(empty($correo) || empty($password)){
    $_SESSION['error'] = "Debes completar todos los campos.";
    header("Location: ../index.php");
    exit();
}

// Aquí apunta al Tomcat y endpoint login
$datos = [
    "correo" => $correo,
    "contrasena" => $password
];

$respuesta = llamarAPI("http://localhost:8080/tema5maven/rest/usuarios/login", "POST", $datos);


if(isset($respuesta['id'])){
    $_SESSION['usuario_id'] = $respuesta['id'];
    $_SESSION['usuario_nombre'] = $correo; 
    header("Location: ../vista/Principal.php");
    exit();
} else {
    $_SESSION['error'] = "Usuario o contraseña incorrectos";
    header("Location: ../index.php");
    exit();
}
?>


