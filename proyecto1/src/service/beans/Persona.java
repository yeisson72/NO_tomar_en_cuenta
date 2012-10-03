package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona {
  private String cedula;
  private String nombre;
  private String telefono;
  private String opcervacion;
  private String tipo;

  public Persona() {
    this.cedula = "9999";
	this.nombre = "";
	this.telefono = "";
	this.opcervacion  = "";
	this.tipo = "1";
  }
  public Persona(String cedula, String nombre,String telefono,
                    String opcervacion, String tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
		this.telefono = telefono;
        this.opcervacion  = opcervacion;
        this.tipo = tipo;
    }
  public void setcedula(String cedula) {this.cedula = cedula;}
  public void setnombre(String nomb) {this.nombre = nomb;}
  public void setopcervacion(String opcervacion) {this.opcervacion = opcervacion;}
  public void settipo(String tipo) {this.tipo = tipo;}
  public void settelefono(String tel) {this.telefono = tel;}
  public String getcedula() { return cedula; }
  public String getnombre() { return nombre; }
  public String getopcervacion() { return opcervacion; }
  public String gettipo() { return tipo; }
  public String getTelefono() { return telefono; }
}