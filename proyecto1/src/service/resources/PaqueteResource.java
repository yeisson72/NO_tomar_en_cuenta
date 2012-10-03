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
import service.beans.Resinto;
import service.beans.Paquete;
import service.storage.PaqueteStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class PaqueteResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String paquete;

    public PaqueteResource(UriInfo uriInfo, Request request, String paquete) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.paquete = paquete;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Paquete getPaquete() {
        Paquete objt = PaqueteStore.getStore().get(paquete);
        if(objt==null)
            throw new NotFoundException("No such Paquete.");
        return objt;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putPaquete(JAXBElement<Paquete> jaxbPaquete) {
        Paquete o = jaxbPaquete.getValue();
        return putAndGetResponse(o);
    }

    @PUT
    public Response putPaquete(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
		Resinto resinto = new Resinto();
		resinto.setcodigo(params.get("codigo"));
        Paquete o = new Paquete(params.get("codigo"), params.get("remitente"), params.get("destinatario"),resinto, params.get("peso"),params.get("cobro"), params.get("monto"));
        return putAndGetResponse(o);
    }

    private Response putAndGetResponse(Paquete o) {
        Response res;
        if(PaqueteStore.getStore().containsKey(o.getcodigo())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        PaqueteStore.getStore().put(o.getcodigo(), o);
        return res;
    }

    @DELETE
    public void deletePersona() {
        Paquete o = PaqueteStore.getStore().remove(paquete);
        if(o==null)
            throw new NotFoundException("No such Paquete.");
    }
}