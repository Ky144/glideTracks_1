package hs.os.skaterverwaltung.boundary.web;


import hs.os.skaterverwaltung.control.ISkaterManagement;
import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.faulttolerance.Retry;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.MultipartForm;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@Path("/web/v1/skater")
@ApplicationScoped
public class SkaterResource {
    private static final Logger LOG = Logger.getLogger(SkaterResource.class);

    @Inject
    ISkaterManagement management;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance addSkater(boolean update, Skater skater);
        public static native TemplateInstance skaters(List<Skater> skaters);
    }

    @GET
    @Retry(maxRetries = 3)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        LOG.info("Loading List of all Skater");
        List<Skater> skatersList = new ArrayList<>(management.getAll());
        return Templates.skaters(skatersList);
    }

    @Path("/add")
    @GET
    @Retry(maxRetries = 3)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("admin")
    public TemplateInstance getAddForm() {
        LOG.info("Loading form to add new Skater");
        return Templates.addSkater(false, null);
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @Retry(maxRetries = 3)
    @Transactional
    @RolesAllowed("admin")
    public Response addSkater(@MultipartForm SkaterForm skaterForm) {
        LOG.info("Versuche einen neuen Skater einzufügen. Vorname: " + skaterForm.vorname + "; Nachname: " + skaterForm.nachname);

        // Validierung der Formulardaten
        if (skaterForm.vorname == null || skaterForm.vorname.isEmpty() || skaterForm.nachname == null || skaterForm.nachname.isEmpty() ||
        skaterForm.disziplin == null || skaterForm.disziplin.isEmpty() || skaterForm.alter == 0 ) {
            LOG.warn("Ungültige Formulardaten: Vorname und Nachname darken nicht leer sein");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        SkaterDTO dto = skaterForm.convertIntoSkaterDTO();
        Skater skater = management.add(dto);
        if (skater == null) {
            LOG.warn("Failed to add new Skater");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        LOG.info("Successfully added Skater. Vorname: " + skater.getVorname() + "; Nachname: " + skater.getNachname() + " ID: " + skater.getId());
        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/skater")).build();
    }



    @Path("/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Retry(maxRetries = 3)
    @RolesAllowed("admin")
    public TemplateInstance getEditForm(@PathParam("id") long id){
        LOG.info("Loading form to edit Skater with id " + id);
        Skater skater = management.get(id);

        // Überprüfen, ob der Skater mit der angegebenen ID gefunden wurde
        if (skater == null) {

            LOG.warn("Skater with ID " + id + " not found.");
        }

        return Templates.addSkater(true, skater);
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @Retry(maxRetries = 3)
    @Transactional
    @RolesAllowed("admin")
    public Response editSkater(@PathParam("id") long id, @MultipartForm SkaterForm skaterForm) {
        LOG.info("Trying to edit Skater with id " + id);
        SkaterDTO dto = skaterForm.convertIntoSkaterDTO();
        Skater skater = management.edit(id, dto);
        if (skater == null) {
            LOG.info("Failed to edit Skater with id " + id);

            return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/skater")).build();
        } else {
            LOG.info("Successfully edited Skater with id " + id);
        }
        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/skater")).build();
    }

    @Path("/{id}/delete")
    @GET
    @Retry(maxRetries = 3)
    @Transactional
    @RolesAllowed("admin")
    public Response deleteSkater(@PathParam("id") long id) {
        if (management.delete(id)) {
            LOG.info("Successfully deleted Skater with id " + id);
        } else {
            LOG.info("Failed to delete Skater with id " + id);

             return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.SEE_OTHER).location(URI.create("/web/v1/skater")).build();
    }


}
