<?php
session_start();
require_once("../controlador/apiHelper.php");

$nombre = $_POST['nombre'] ?? '';
$correo = $_POST['email'] ?? '';
$password = $_POST['password'] ?? '';
$password2 = $_POST['password2'] ?? '';

if(empty($nombre) || empty($correo) || empty($password) || empty($password2)){
    $_SESSION['error'] = "Todos los campos son obligatorios.";
    header("Location: ../vista/Registro.php"); 
    exit();
}

if($password !== $password2){
    $_SESSION['error'] = "Las contraseñas no coinciden.";
    header("Location: ../vista/Registro.php"); 
    exit();
}

$datos = [
    "nombre" => $nombre,
    "correo" => $correo,
    "contrasena" => $password
];

$respuesta = llamarAPI("http://localhost:8080/tema5maven/rest/usuarios", "POST", $datos);

// Si la api me devuelve el id inicio sesion directamente
if(isset($respuesta['id'])){
    $_SESSION['usuario_id'] = $respuesta['id'];
    $_SESSION['usuario_nombre'] = $nombre; // Guardamos el nombre del usuario
    $_SESSION['success'] = "Usuario registrado correctamente y ya has iniciado sesión.";
    
    // Redirige directamente a Principal.php
    header("Location: ../vista/Principal.php");
    exit();
} else {
    $_SESSION['error'] = "Error al registrar usuario";
    header("Location: ../vista/Registro.php");
    exit();
}
?>






