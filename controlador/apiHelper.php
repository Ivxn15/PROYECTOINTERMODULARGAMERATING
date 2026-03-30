<?php
function llamarAPI($url, $metodo = "GET", $datos = null){
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);

    if($metodo === "POST"){
        curl_setopt($ch, CURLOPT_POST, true);
        if($datos) curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($datos));
    } elseif($metodo === "DELETE"){
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
        if($datos) curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($datos));
    }

    $resultado = curl_exec($ch);
    curl_close($ch);

    return json_decode($resultado, true);
}
?>