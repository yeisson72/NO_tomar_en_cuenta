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
import service.beans.Vehiculo;
import service.storage.VehiculoStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class VehiculoResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String vehiculo;

    public VehiculoResource(UriInfo uriInfo, Request request, String vehiculo) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.vehiculo = vehiculo;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Vehiculo getVehiculo() {
        Vehiculo objt =VehiculoStore.getStore().get(vehiculo);
        if(objt==null)
            throw new NotFoundException("No such Vehiculo.");
        return objt;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putVehiculo(JAXBElement<Vehiculo> jaxbVehiculo) {
        Vehiculo o = jaxbVehiculo.getValue();
        return putAndGetResponse(o);
    }

    @PUT
    public Response putVehiculo(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
        Vehiculo o = new Vehiculo(params.get("placa"), params.get("ruta"));
        return putAndGetResponse(o);
    }

    private Response putAndGetResponse(Vehiculo o) {
        Response res;
        if(VehiculoStore.getStore().containsKey(o.getplaca())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        VehiculoStore.getStore().put(o.getplaca(), o);
        return res;
    }

    @DELETE
    public void deleteVehiculo() {
        Vehiculo o = VehiculoStore.getStore().remove(vehiculo);
        if(o==null)
            throw new NotFoundException("No such Vehiculo.");
    }
}