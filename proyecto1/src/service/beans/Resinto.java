package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resinto {
  private String codigo;
  private String zona;
  private String calle;
  private String avenida;
  private String distancia;
  private String latitud;
  private String longitud;
  private String razon_social;

  public Resinto() {
	
	this.codigo = "9999";
	this.zona = "";
	this.calle = "";
	this.zona = "";
	this.avenida  = "";
	this.distancia = "9999";
	this.latitud = "";
	this.longitud = "";
	this.razon_social = "";
	
  }
  public Resinto(String codigo, String zona, String calle,
                    String avenida, String distancia, String latitud,
                    String longitud, String razon_social) {
        this.codigo = codigo;
        this.zona = zona;
        this.calle = calle;
        this.zona = zona;
        this.avenida  = avenida;
        this.distancia = distancia;
        this.latitud = latitud;
        this.longitud = longitud;
		this.razon_social = razon_social;
    }
  public void setcodigo(String codigo) {this.codigo = codigo;}
  public void setcalle(String ced) {this.calle = ced;}
  public void setzona(String nomb) {this.zona = nomb;}
  public void setavenida(String tit) {this.avenida = tit;}
  public void setdistancia(String distancia) {this.distancia = distancia;}
  public void setlatitud(String corr) {this.latitud = corr;}
  public void setlongitud(String longitud) {this.longitud = longitud;}
  public void setrazon_social(String longitud) {this.longitud = longitud;}
  public String getcodigo() { return codigo; }
  public String getcalle() { return calle; }
  public String getzona() { return zona; }
  public String getavenida() { return avenida; }
  public String getdistancia() { return distancia; }
  public String getlatitud() { return latitud; }
  public String getlongitud() { return longitud; }
  public String getrazon_social() { return razon_social; }
}