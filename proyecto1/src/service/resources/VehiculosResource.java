package service.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import service.beans.Vehiculo;
import service.storage.VehiculoStore;

@Path("/vehiculos")
public class VehiculosResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Vehiculo> getoficinas() {
        List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        vehiculos.addAll( VehiculoStore.getStore().values() );
        return vehiculos;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = VehiculoStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newOficina(
            @FormParam("ruta") String ruta,
            @FormParam("placa") String placa,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
		
        Vehiculo obj = new Vehiculo(placa,ruta);
        VehiculoStore.getStore().put(placa, obj);

        URI uri = uriInfo.getAbsolutePathBuilder().path(placa).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_vehiculo.html");
    }

    @Path("{vehiculo}")
    public VehiculoResource getOficina(
            @PathParam("vehiculo") String vehiculo) {
        return new VehiculoResource(uriInfo, request, vehiculo);
    }
}