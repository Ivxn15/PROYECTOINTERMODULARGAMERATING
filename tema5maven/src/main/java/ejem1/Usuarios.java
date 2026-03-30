package ejem1;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuarios {
    private int id;
	private String nombre;
	private String correo;
	private String contrasena;
	
	
	public Usuarios() {
		
	}
	
	public Usuarios(int id, String nombre, String correo, String contrasena) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}

