package com.example.gamerating;

public class Resena {

    private int id;
    private int usuarioId;
    private String juego;
    private int puntuacion;
    private String comentario;
    private String fecha;
    private String nombre;

    public Resena(int id, int usuarioId, String juego, int puntuacion, String comentario, String fecha, String nombre) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.juego = juego;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getId() { return id; }
    public int getUsuarioId() { return usuarioId; }
    public String getJuego() { return juego; }
    public int getPuntuacion() { return puntuacion; }
    public String getComentario() { return comentario; }
    public String getFecha() { return fecha; }

    public void setId(int id) { this.id = id; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public void setJuego(String juego) { this.juego = juego; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

}
