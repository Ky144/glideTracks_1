package hs.os.turnierverwaltung.boundary.web;

import hs.os.turnierverwaltung.control.ITurnierManagement;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import hs.os.turnierverwaltung.entity.Turnier;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.MultipartForm;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;



public class TurnierResource {}
    /*


    private static final Logger LOG = Logger.getLogger(TurnierResource.class);

    @CheckedTemplate
    public static class Templates
    {
        public static native TemplateInstance message(String message);
        public static native TemplateInstance turniere(boolean filtered, String filter, Collection<Turnier> courses, boolean admin);
        public static native TemplateInstance addTurnier(boolean update, Turnier turnier);
    }

    @Inject
    private ITurnierManagement management;



    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @Retry(maxRetries = 4)
    @RolesAllowed("admin")
    public TemplateInstance getAddForm() {
        LOG.log(Logger.Level.INFO, "Loading form to add new Turnier");
        return Templates.addTurnier(false, null);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    @Retry(maxRetries = 4)
    @RolesAllowed("admin")
    public Response addTurnier(@MultipartForm TurnierForm turnierForm) {
        LOG.log(Logger.Level.INFO, "Trying to add a new Turnier");
        @Valid TurnierDTO dto = turnierForm.convertIntoTurnierDTO();
        Turnier turnier = management.add(dto);

        if (turnier == null) {
            LOG.info("Failed to add new Turnier");
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

        LOG.log(Logger.Level.INFO, "Successfully added Turnier with ID: " + turnier.getId());
        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/turnier")).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    @Retry(maxRetries = 4)
    @RolesAllowed("admin")
    public TemplateInstance getEditForm(@PathParam("id") long id) {
        LOG.info("Loading form to edit Turnier with ID: " + id);
        Turnier turnier = management.get(id);
        return Templates.addTurnier(true, turnier);
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @Retry(maxRetries = 4)
    @Transactional
    @RolesAllowed("admin")
    public Response editTurnier(@PathParam("id") long id, @MultipartForm TurnierForm turnierForm) {
        LOG.info("Trying to edit Turnier with id " + id);
        TurnierDTO dto = turnierForm.convertIntoTurnierDTO();
        Turnier turnier = management.edit(id, dto);

        if (turnier == null) {
            LOG.info("Failed to edit Turnier with id " + id);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        } else {
            LOG.info("Successfully edited Turnier with id " + id);
        }

        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/turnier")).build();
    }


    @GET
    @Path("/{id}/delete")
    @Transactional
    @Retry(maxRetries = 4)
    @RolesAllowed("admin")
    public Response deleteTurnier(@PathParam("id") long id) {
        LOG.info("Trying to delete Turnier with id: " + id);

        if (!management.delete(id)) {
            LOG.info("Failed to delete Turnier with id: " + id);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        } else {
            LOG.info("Successfully deleted Turnier with id: " + id);
        }

        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/turnier")).build();
    }

}
*/