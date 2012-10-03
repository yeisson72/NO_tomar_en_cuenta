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
import service.beans.Resinto;
import service.beans.Oficina;
import service.storage.OficinaStore;

@Path("/oficinas")
public class OficinasResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Oficina> getoficinas() {
        List<Oficina> oficinas = new ArrayList<Oficina>();
        oficinas.addAll( OficinaStore.getStore().values() );
        return oficinas;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = OficinaStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newOficina(
            @FormParam("codigo") String codigo,
            @FormParam("telefono") String telefono,
            @FormParam("resinto") String resinto,
            @FormParam("persona") String persona,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
		Persona p = new Persona();
		p.setcedula(persona);
		
		Resinto r = new Resinto();
		r.setcodigo(resinto);

        Oficina obj = new Oficina(codigo,telefono,r,p);
        OficinaStore.getStore().put(codigo, obj);

        URI uri = uriInfo.getAbsolutePathBuilder().path(codigo).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_oficina.html");
    }

    @Path("{oficina}")
    public OficinaResource getOficina(
            @PathParam("oficina") String oficina) {
        return new OficinaResource(uriInfo, request, oficina);
    }
}