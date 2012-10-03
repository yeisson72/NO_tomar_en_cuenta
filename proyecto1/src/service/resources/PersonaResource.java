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
import service.storage.PersonaStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class PersonaResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String persona;

    public PersonaResource(UriInfo uriInfo, Request request, String persona) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.persona = persona;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Persona getPersona() {
        Persona objt = PersonaStore.getStore().get(persona);
        if(objt==null)
            throw new NotFoundException("No such Persona.");
        return objt;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putPersona(JAXBElement<Persona> jaxbPersona) {
        Persona o = jaxbPersona.getValue();
        return putAndGetResponse(o);
    }

    @PUT
    public Response putPersona(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
        Persona o = new Persona(params.get("cedula"), params.get("nombre"), params.get("telefono"), params.get("opcervacion"), params.get("tipo"));
        return putAndGetResponse(o);
    }

    private Response putAndGetResponse(Persona o) {
        Response res;
        if(PersonaStore.getStore().containsKey(o.getcedula())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        PersonaStore.getStore().put(o.getcedula(), o);
        return res;
    }

    @DELETE
    public void deletePersona() {
        Persona o = PersonaStore.getStore().remove(persona);
        if(o==null)
            throw new NotFoundException("No such Persona.");
    }
}