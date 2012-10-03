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
import service.storage.PersonaStore;

@Path("/Personas")
public class PersonasResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Persona> getpersonas() {
        List<Persona> personas = new ArrayList<Persona>();
        personas.addAll( PersonaStore.getStore().values() );
        return personas;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = PersonaStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newPersona(
            @FormParam("cedula") String cedula,
            @FormParam("nombre") String nombre,
            @FormParam("telefono") String telefono,
            @FormParam("opcervacion") String opcervacion,
            @FormParam("tipo") String tipo,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
        Persona obj = new Persona(cedula,nombre,telefono,opcervacion,tipo);
        PersonaStore.getStore().put(cedula, obj);

        URI uri = uriInfo.getAbsolutePathBuilder().path(cedula).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_persona.html");
    }

    @Path("{persona}")
    public PersonaResource getPersona(
            @PathParam("persona") String persona) {
        return new PersonaResource(uriInfo, request, persona);
    }
}