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
import service.beans.Resinto;
import service.beans.Oficina;
import service.storage.OficinaStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class OficinaResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String oficina;

    public OficinaResource(UriInfo uriInfo, Request request, String oficina) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.oficina = oficina;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Oficina getOficina() {
        Oficina objt = OficinaStore.getStore().get(oficina);
        if(objt==null)
            throw new NotFoundException("No such Oficina.");
        return objt;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putOficina(JAXBElement<Oficina> jaxbOficina) {
        Oficina o = jaxbOficina.getValue();
        return putAndGetResponse(o);
    }

    @PUT
    public Response putOficina(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
		Resinto r = new Resinto();
		r.setcodigo(params.get("resinto"));
		Persona p = new Persona();
		p.setcedula(params.get("cedula"));
        Oficina o = new Oficina(params.get("codigo"), params.get("telefono"), r, p);
        return putAndGetResponse(o);
    }

    private Response putAndGetResponse(Oficina o) {
        Response res;
        if(OficinaStore.getStore().containsKey(o.getcodigo())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        OficinaStore.getStore().put(o.getcodigo(), o);
        return res;
    }

    @DELETE
    public void deleteOficina() {
        Oficina o = OficinaStore.getStore().remove(oficina);
        if(o==null)
            throw new NotFoundException("No such Oficina.");
    }
}