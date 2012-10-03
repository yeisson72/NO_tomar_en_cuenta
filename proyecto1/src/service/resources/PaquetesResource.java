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
import service.beans.Paquete;
import service.storage.PaqueteStore;

@Path("/paquetes")
public class PaquetesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Paquete> getpaquetes() {
        List<Paquete> paquetes = new ArrayList<Paquete>();
        paquetes.addAll( PaqueteStore.getStore().values() );
        return paquetes;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = PaqueteStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newPaqute(
            @FormParam("codigo") String codigo,
            @FormParam("remitente") String remitente,
            @FormParam("destinatario") String destinatario,
            @FormParam("resinto") String resinto,
			@FormParam("peso") String peso,
            @FormParam("cobro") String cobro,
			@FormParam("monto") String monto,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
		
		Resinto r = new Resinto();
		r.setcodigo(resinto);
		
        Paquete obj = new Paquete(codigo,remitente,destinatario,r,peso,cobro,monto);
        PaqueteStore.getStore().put(codigo, obj);

        URI uri = uriInfo.getAbsolutePathBuilder().path(codigo).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_paquete.html");
    }

    @Path("{paquete}")
    public PaqueteResource getPaquete(
            @PathParam("paquete") String paquete) {
        return new PaqueteResource(uriInfo, request, paquete);
    }
}