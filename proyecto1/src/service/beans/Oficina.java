package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Oficina {
  private String codigo;
  private String telefono;
  private Resinto resinto;
  private Persona persona;

  public Oficina() {
    resinto = new Resinto();
	persona = new Persona();
	this.codigo = "999";
    this.telefono = "";
  }
  public Oficina(String codigo, String telefono, Resinto resinto, Persona persona) {
        this.codigo = codigo;
        this.telefono = telefono;
        this.resinto = resinto;
		this.persona = persona;
    }
  public void setcodigo(String codigo) {this.codigo = codigo;}
  public void settelefono(String tel) {this.telefono = tel;}
  public String getcodigo() { return codigo; }
  public String gettelefono() { return telefono; }
  public void setresinto(Resinto resinto) { this.resinto = resinto;}
  public void setpersona(Persona persona) { this.persona = persona;}
  public Resinto getresinto() { return resinto; }
  public Persona getpersona() { return persona; }
}