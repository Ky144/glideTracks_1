package hs.os.turnierverwaltung.boundary.rest;

import com.oracle.svm.core.annotate.Inject;

import hs.os.skaterverwaltung.control.ISkaterManagement;
import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.ITurnierManagement;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import hs.os.turnierverwaltung.entity.Turnier;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/turnier")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@OpenAPIDefinition(
        info = @Info
        (
                title = "Turnier management",
        description = "Enthält Methoden zur Turnierverwaltung",
        version = "1.0.0"
)
)
public class TurnierResource {

    @Inject
    ITurnierManagement turnierManagement;
    @Inject
    ISkaterManagement skaterManagement;
    private static final Logger LOG = Logger.getLogger(TurnierResource.class);


    @GET
    @RolesAllowed({"admin", "skater"})
    @Operation(summary = "Gets all Turniere", description = "Only Turnier data without skater will returned.")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Erfolg", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Turnier.class))),
                    @APIResponse(responseCode = "204", description="No turniere found")
            })
    @Retry(maxRetries = 3)
    @Transactional
    public Response getAllTurniere(){
        LOG.info("Auflistung aller Turniere aus der Database");
        Collection<Turnier> turniere= this.turnierManagement.getAll();
        if(turniere.isEmpty()){
            LOG.error("leere Turnier-Liste");
            return Response.noContent().build();
        }
        LOG.info("Erfolgreiche Auflistung aller Turniere aus der Database");
        return Response.ok(turnierManagement.getAll()).build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({"admin", "skater"})
    @Operation(summary = "Gets a specific turnier", description = "Gets a turnier with speficifc ID")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Turnier.class))),
                    @APIResponse(responseCode = "404", description="Turnier does not exist.")
            })

    @Transactional
    public Response getTurnier(@PathParam("id") long id)
    {
        LOG.info("Auflistung eines Turniers anhand seiner ID aus der Database");
        Turnier turnier= this.turnierManagement.get(id);
        if(turnier == null){
            LOG.error("Übergebene ID des Turniers ist in der Database nicht vorhanden");
            return Response.noContent().build();
        }
        LOG.info("Erfolgreiche Auflistung eines Turniers anhand seiner ID aus der Database");
        return Response.ok(turnier).build();
    }

    @POST
    @Path("{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Add Turnier", description = "Data in body indicates the new data of the Turnier.")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Turnier.class))),
                    @APIResponse(responseCode = "500", description="Error while creating new Turnier.")
            })
    @Retry(maxRetries = 3)
    @Transactional
    public Response addTurnier(@Valid TurnierDTO dto)
    {
        LOG.info("Das Hinzufügen eines neuen Turniers zur Database");
        Turnier turnier = this.turnierManagement.add(dto);
        if(turnier == null){
            LOG.error("das Hinzufügen eines neuen Turniers zur Database ist gescheitert");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        LOG.info("Das Hinzufügen eines neuen Turniers zur Database war erfolgreich");
        return Response.ok(turnier).build();
    }
    @PUT
    @Path("{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Edit Turnier", description = "Edits turnier with specific ID. Data in body indicates the new data of the turnier.")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Turnier.class))),
                    @APIResponse(responseCode = "404", description="Turnier does not exist.")
            })

    @Retry(maxRetries = 3)
    @Transactional
    public Response editTurnier(@PathParam("id") long id, @Valid TurnierDTO dto)
    {
        LOG.info("Bearbeite eines Turniers anhand seiner id: " + id);
        Turnier turnier = this.turnierManagement.edit(id,dto);
        if(turnier == null){
            LOG.error("Es wurde kein Turnier mit dieser id " + id + "gefunden");
            return Response.noContent().build();
        }
        LOG.info("Bearbeitung des Skaters verlief erfolgreich");
        return Response.ok(turnier).build();
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Delete course", description = "Deletes a Turnier with specific ID.")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
                    @APIResponse(responseCode = "404", description="Course does not exist.")
            })

    @Retry(maxRetries = 3)
    @Transactional
    public Response delete(@PathParam("id") long id)
    {
        LOG.info("Lösche ein Turnier anhand seiner id: " + id);
        boolean deleted = this.turnierManagement.delete(id);
        if (deleted)
        {
            LOG.info("Das Löschen des Turniers verlief erfolgreich");
            return Response.ok().build();
        }
        LOG.error("Es wurde kein Turnier mit dieser id " + id + "gefunden");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/search")
    @RolesAllowed({"admin", "skater"})
    @Operation(summary = "Search Turnier by name", description = "Returns Turnier whose names match the search term. Only course data without skater will returned.")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skater.class))),
                    @APIResponse(responseCode = "204", description="No courses found.")
            })

    @Retry(maxRetries = 3)
    @Transactional
    public Response searchByTurnierName(@QueryParam("s") String search)
    {
        LOG.info("Suche Turnier mit: " + search + ".");
        Collection<Turnier> turnier = this.turnierManagement.searchByTurnierName(search);

        if (turnier.isEmpty())
        {
            LOG.error("Kein passendes Turnier mit dem Begriff: " + search + "gefunden.");
            return Response.noContent().build();
        }

        LOG.info("Suche verlief erfolgreich");
        return Response.ok(turnier).build();
    }
    /**
     * Ermittelt alle Kurse, in denen ein Skater angemeldet ist.
     * @return Gibt die Kurse zurück.
     */
    @GET
    @Path("/skater/{id}")
    @RolesAllowed({"admin", "skater"})
    @Operation(summary = "Turniere of skater", description = "Returns all turniere where skater is signed in.")
    @APIResponses(value =
            {
                    @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
                    @APIResponse(responseCode = "204", description="Skater is not signed in any turniere.")
            })
    @Retry(maxRetries = 3)
    @Transactional
    public Response getTurniereOfSkater(@PathParam("id") long skaterId) {
        LOG.info("Ermittlung der Turniere für Skater mit ID: " + skaterId);
        Collection<Turnier> turniere = this.turnierManagement.getTurnierOfSkater(skaterId);

        if (turniere.isEmpty()) {
            LOG.error("Der Skater mit ID " + skaterId + " ist in keinem Turnier angemeldet.");
            return Response.noContent().build();
        }

        LOG.info("Ermittlung der Turniere für Skater mit ID " + skaterId + " war erfolgreich.");
        return Response.ok(turniere).build();
    }

    @PUT
    @Path("/{id}/sign-in/{skaterId}")
    @RolesAllowed("admin")
    @Operation(summary = "Sign-in Skater to Turnier", description = "Signs-in a Skater to a Turnier with specific IDs.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Turnier.class))),
            @APIResponse(responseCode = "404", description = "Turnier or Skater does not exist.")
    })
    @Retry(maxRetries = 3)
    @Transactional
    public Response signInSkater(
            @PathParam("id") long turnierId,
            @PathParam("skaterId") long skaterId
    ) {
        LOG.info("Anmeldung eines Skaters zu einem Turnier. Turnier-ID: " + turnierId + ", Skater-ID: " + skaterId);
        Turnier turnier = turnierManagement.get(turnierId);
        Skater skater = skaterManagement.get(skaterId);

        if (turnier == null || skater == null) {
            LOG.error("Das Turnier oder der Skater existiert nicht.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Skater zum Turnier hinzufügen
        turnier.addSkater(skater);
        LOG.info("Der Skater wurde erfolgreich zum Turnier angemeldet.");
        return Response.ok(turnier).build();
    }

    @PUT
    @Path("/{id}/sign-out/{skaterId}")
    @RolesAllowed("admin")
    @Operation(summary = "Sign-out Skater from Turnier", description = "Signs-out a Skater from a Turnier with specific IDs.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Turnier.class))),
            @APIResponse(responseCode = "404", description = "Turnier or Skater does not exist.")
    })
    @Retry(maxRetries = 3)
    @Transactional
    public Response signSkaterOut(
            @PathParam("id") long turnierId,
            @PathParam("skaterId") long skaterId
    ) {
        LOG.info("Abmeldung eines Skaters von einem Turnier. Turnier-ID: " + turnierId + ", Skater-ID: " + skaterId);
        Turnier turnier = turnierManagement.get(turnierId);
        Skater skater = skaterManagement.get(skaterId);

        if (turnier == null || skater == null) {
            LOG.error("Das Turnier oder der Skater existiert nicht.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Skater vom Turnier entfernen
        turnier.removeSkater(skater);
        LOG.info("Der Skater wurde erfolgreich vom Turnier abgemeldet.");
        return Response.ok(turnier).build();
    }
}




