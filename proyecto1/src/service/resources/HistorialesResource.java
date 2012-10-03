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
import service.beans.Persona;
import service.beans.Paquete;
import service.beans.Historial;
import service.storage.HistorialStore;

@Path("/historial")
public class HistorialesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Historial> gethistoriales() {
        List<Historial> historiales = new ArrayList<Historial>();
        historiales.addAll( HistorialStore.getStore().values() );
        return historiales;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = HistorialStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newOficina(
            @FormParam("codigo") String codigo,
            @FormParam("persona") String persona,
            @FormParam("fecha") String fecha,
            @FormParam("descripcion") String descripcion,
			@FormParam("paquete") String paquete,			
            @Context HttpServletResponse servletResponse
    ) throws IOException {
		Persona p = new Persona();
		p.setcedula(persona);
		
		Paquete q = new Paquete();
		q.setcodigo(paquete);

        Historial obj = new Historial(codigo,p,fecha,descripcion,q);
        HistorialStore.getStore().put(codigo, obj);

        URI uri = uriInfo.getAbsolutePathBuilder().path(codigo).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_historial.html");
    }

    @Path("{historial}")
    public HistorialResource getHistorial(
            @PathParam("historial") String historial) {
        return new HistorialResource(uriInfo, request, historial);
    }
}