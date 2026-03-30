package ejem1;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Car {
    private String marca;
    private String modelo;

    public Car() {
    }

    public Car(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
