package hs.os.skaterverwaltung.boundary.rest;

import com.oracle.svm.core.annotate.Inject;
import hs.os.skaterverwaltung.control.ISkaterManagement;
import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import org.jboss.logging.Logger;

/**
 * API Funktionalitäten zur Skaterverwaltung.
 **/

@Path("/skater")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SkaterResource {

    private static final Logger LOG = Logger.getLogger(SkaterResource.class);
    @Inject
    private ISkaterManagement management;

    public SkaterResource(ISkaterManagement management) {
        this.management = management;
    }

    @GET
    @Operation(summary = "Gets all Skater", description = "Lists all existing Skater")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skater.class))),
            @APIResponse(responseCode = "204", description="No Skater found")
    })
    @Retry(maxRetries = 3)
    @RolesAllowed("admin")
    public Response getAllSkater(){
        LOG.info("Auflistung aller Skater aus der Database");
        Collection<Skater> skater= this.management.getAll();

        if(skater.isEmpty()){
            LOG.error("leere Skater-Liste");
            return Response.noContent().build();
        }
        LOG.info("Erfolgreiche Auflistung aller Skater aus der Database");
        return Response.ok(management.getAll()).build();
    }

    @GET
    @Operation(summary = "Gets a Skater", description = "Gets a Skater for a given id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skater.class))),
            @APIResponse(responseCode = "404", description="No Skater found for Id")
    })
    @Path("{id}")
    @Retry(maxRetries = 3)
    @RolesAllowed("admin")
    public Response getSkater(@PathParam("id") long id)
    {
        LOG.info("Auflistung eines Skaters anhand seiner ID aus der Database");
        Skater skater= this.management.get(id);
        if(skater == null){
            LOG.error("Übergebene ID des Skaters ist in der Database nicht vorhanden");
            return Response.noContent().build();
        }
        LOG.info("Erfolgreiche Auflistung eines Skaters anhand seiner ID aus der Database");
        return Response.ok(skater).build();
    }
    @POST
    @Operation(summary = "Adds a new Skater", description = "Creates and persists a new Skater.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skater.class))),
            @APIResponse(responseCode = "500", description="Skater could not be created")
    })
    @Retry(maxRetries = 3)
    @Transactional
    @RolesAllowed("admin")
    public Response add(@Valid SkaterDTO dto){
        LOG.info("Das Hinzufügen eines neuen Skaters zur Database");
        Skater skater = this.management.add(dto);
        if(skater == null){
            LOG.error("das Hinzufügen eines neuen Skaters zur Database ist gescheitert");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        LOG.info("Das Hinzufügen eines neuen Skaters zur Database war erfolgreich");
        return Response.status(Response.Status.CREATED).entity(skater).build();
    }

    @PUT
    @Operation(summary = "Updates a Skater", description = "Updates an existing Skater with given Id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skater.class))),
            @APIResponse(responseCode = "404", description="No Skater found for id")
    })
    @Path("{id}")
    @Transactional
    @Retry(maxRetries = 3)
    @RolesAllowed("admin")
    public Response editSkater(@PathParam("id") long id, @Valid SkaterDTO dto)
    {
        LOG.info("Bearbeite einen Skater anhand seiner id: " + id);
        Skater skater = this.management.edit(id,dto);
        if(skater == null){
            LOG.error("Es wurde kein Skater mit dieser id " + id + "gefunden");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOG.info("Bearbeitung des Skaters verlief erfolgreich");
        return Response.ok(skater).build();
    }

    @DELETE
    @Operation(summary = "Deletes a Skater", description = "Deletes a Skater with a given id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skater.class))),
            @APIResponse(responseCode = "404", description="No Skater found for id")
    })
    @Path("{id}")
    @Retry(maxRetries = 3)
    @Transactional
    @RolesAllowed("admin")
    public Response deleteSkater(@PathParam("id") long id){
        LOG.info("Lösche einen Skater anhand seiner id: " + id);
        boolean deleted = this.management.delete(id);
        if(deleted){
            LOG.info("Das Löschen des Skaters verlief erfolgreich");
            return Response.ok().build();
        }
        LOG.error("Es wurde kein Skater mit dieser id " + id + "gefunden");
        return Response.status(Response.Status.NOT_FOUND).build();
    }






}
