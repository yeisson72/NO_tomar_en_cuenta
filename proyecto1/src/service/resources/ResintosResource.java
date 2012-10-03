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
import service.beans.Resinto;
import service.storage.ResintoStore;

@Path("/Resintos")
public class ResintosResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resinto> getResintos() {
        List<Resinto> resintos = new ArrayList<Resinto>();
        resintos.addAll( ResintoStore.getStore().values() );
        return resintos;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = ResintoStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newResinto(
            @FormParam("codigo") String codigo,
			@FormParam("zona") String zona,
            @FormParam("calle") String calle,
            @FormParam("avenida") String avenida,
            @FormParam("distancia") String distancia,
            @FormParam("latitud") String latitud,
            @FormParam("longitud") String longitud,
            @FormParam("razon_social") String razon_social,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
        Resinto obj = new Resinto(codigo,zona,calle,avenida,distancia,latitud,longitud,razon_social);
        ResintoStore.getStore().put(codigo, obj);

        URI uri = uriInfo.getAbsolutePathBuilder().path(codigo).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_resinto.html");
    }

    @Path("{resinto}")
    public ResintoResource getResinto(
            @PathParam("resinto") String resinto) {
        return new ResintoResource(uriInfo, request, resinto);
    }
}