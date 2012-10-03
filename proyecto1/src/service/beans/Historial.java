package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Historial {
  private String codigo;
  private String fecha;
  private String descripcion;
  private Persona persona;
  private Paquete paquete;

  public Historial() {
    persona = new Persona();
	this.codigo = "9999";
	this.fecha = "";
	this.descripcion = "";
  }
  public Historial(String codigo, Persona persona,String fecha, String descripcion,Paquete paquete
					 ) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
		this.persona = persona;
		this.paquete = paquete;
    }
  public void setcodigo(String codigo) {this.codigo = codigo;}
  public void setfecha(String fecha) {this.fecha = fecha;}
  public void setdescripcion(String descripcion) {this.descripcion = descripcion;}
  public String getcodigo() { return codigo; }
  public String getfecha() { return fecha; }
  public String getdescripcion() { return descripcion; }
  public void setpersona(Persona persona) { this.persona = persona;}
  public Persona getpersona() { return persona; }
  public void setpaquete(Paquete paquete) { this.paquete = paquete;}
  public Paquete getpaquete() { return paquete; }
}