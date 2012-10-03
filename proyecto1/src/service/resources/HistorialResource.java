package service.resources;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import service.beans.Persona;
import service.beans.Paquete;
import service.beans.Historial;
import service.storage.HistorialStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class HistorialResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String historial;

    public HistorialResource(UriInfo uriInfo, Request request, String historial) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.historial = historial;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Historial getHistorial() {
        Historial objt = HistorialStore.getStore().get(historial);
        if(objt==null)
            throw new NotFoundException("No such Historial.");
        return objt;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putHistorial(JAXBElement<Historial> jaxbHistorial) {
        Historial o = jaxbHistorial.getValue();
        return putAndGetResponse(o);
    }

    @PUT
    public Response putHistorial(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
		Paquete q = new Paquete();
		q.setcodigo(params.get("paquete"));
		Persona p = new Persona();
		p.setcedula(params.get("cedula"));
        Historial o = new Historial(params.get("codigo"),p, params.get("fecha"), params.get("descripcion"), q);
        return putAndGetResponse(o);
    }

    private Response putAndGetResponse(Historial o) {
        Response res;
        if(HistorialStore.getStore().containsKey(o.getcodigo())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        HistorialStore.getStore().put(o.getcodigo(), o);
        return res;
    }

    @DELETE
    public void deleteHistorial() {
        Historial o = HistorialStore.getStore().remove(historial);
        if(o==null)
            throw new NotFoundException("No delete historial.");
    }
}