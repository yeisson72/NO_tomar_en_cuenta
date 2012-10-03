package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paquete {
  private String codigo;
  private String remitente;
  private String destinatario;
  private String peso;
  private String cobro;
  private String monto;
  private Resinto resintos;

  public Paquete() {
    resintos = new Resinto();
	this.codigo = "999";
        this.destinatario = "";
        this.remitente = "";
        this.peso  = "";
        this.cobro = "";
        this.monto = "0";
  }
  public Paquete(String codigo, String remitente, String destinatario,Resinto resintos,
                    String peso, String cobro, String monto) {
        this.codigo = codigo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.peso  = peso;
        this.cobro = cobro;
        this.monto = monto;
        this.resintos = resintos;
    }
  public void setcodigo(String codigo) {this.codigo = codigo;}
  public void setremitente(String remitente) {this.remitente = remitente;}
  public void setdestinatario(String destinatario) {this.destinatario = destinatario;}
  public void setpeso(String peso) {this.peso = peso;}
  public void setcobro(String cobro) {this.cobro = cobro;}
  public void setmonto(String monto) {this.monto = monto;}
  public String getcodigo() { return codigo; }
  public String getremitente() { return remitente; }
  public String getdestinatario() { return destinatario; }
  public String getpeso() { return peso; }
  public String getcobro() { return cobro; }
  public String getmonto() { return monto; }
  public Resinto getresintos() { return resintos; }
  public void setresintos(Resinto resintos) { this.resintos = resintos;}
  public Resinto getresinto() { return resintos; }
}