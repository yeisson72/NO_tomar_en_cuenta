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
import service.storage.ResintoStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class ResintoResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String resinto;

    public ResintoResource(UriInfo uriInfo, Request request, String resinto) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.resinto = resinto;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Resinto getresinto() {
        Resinto obj = ResintoStore.getStore().get(resinto);
        if(obj==null)
            throw new NotFoundException("No such resinto.");
        return obj;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putResinto(JAXBElement<Resinto> jaxbResinto) {
        Resinto o = jaxbResinto.getValue();
        return putAndGetResponse(o);
    }

    @PUT
    public Response putResinto(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
        Resinto o = new Resinto(params.get("codigo"), params.get("zona"), params.get("calle"), params.get("avenida"), params.get("distancia"), params.get("latitud"), params.get("longitud"),
                params.get("razon_social"));
        return putAndGetResponse(o);
    }

    private Response putAndGetResponse(Resinto o) {
        Response res;
        if(ResintoStore.getStore().containsKey(o.getcodigo())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        ResintoStore.getStore().put(o.getcodigo(), o);
        return res;
    }

    @DELETE
    public void deleteresinto() {
        Resinto o = ResintoStore.getStore().remove(resinto);
        if(o==null)
            throw new NotFoundException("No such resinto.");
    }
}