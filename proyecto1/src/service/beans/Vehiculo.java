package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehiculo {
  private String ruta;
  private String placa;

  public Vehiculo() {
	this.ruta = "";
    this.placa = "yko404";
  }
  public Vehiculo(String placa, String ruta ) {
        this.ruta = ruta;
        this.placa = placa;
    }
  public void setruta(String ruta) {this.ruta = ruta;}
  public void setplaca(String placa) {this.placa = placa;}
  public String getruta() { return ruta; }
  public String getplaca() { return placa; }
}