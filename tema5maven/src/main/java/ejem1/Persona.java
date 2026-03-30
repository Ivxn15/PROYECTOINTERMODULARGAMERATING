package ejem1;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona   {
    private int id;
    private String nombre;
    private Boolean casado;
    private String sexo;
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
    public Boolean getCasado() {
        return casado;
    }
    public void setCasado(Boolean casado) {
        this.casado = casado;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Persona(){
        
    }
}
