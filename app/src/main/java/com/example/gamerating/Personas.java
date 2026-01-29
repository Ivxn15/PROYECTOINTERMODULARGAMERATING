package com.example.gamerating;

public class Personas {
    String nombre;
    String PrimerApellido;
    String SegundoApellido;
    String email;
    String contraseña;

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getPrimerApellido(){
        return PrimerApellido;
    }
    public void setPrimerApellido(String PrimerApellido){
        this.PrimerApellido = PrimerApellido;
    }
    public String getSegundoApellido(){
        return SegundoApellido;
    }
    public void setSegundoApellido(String SegundoApellido){
        this.SegundoApellido=SegundoApellido;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getContraseña(){
        return contraseña;
    }
    public void setContraseña(String contraseña){
        this.email=email;
    }


}
