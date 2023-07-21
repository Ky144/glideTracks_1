package hs.os.turnierverwaltung.boundary;

import com.oracle.svm.core.annotate.Inject;
import hs.os.skaterverwaltung.control.ISkaterManagement;
import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.ITurnierManagement;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import hs.os.turnierverwaltung.entity.Turnier;
import hs.os.skaterverwaltung.boundary.SkaterResource;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/turnier")
@ApplicationScoped
public class TurnierResource {
    @Inject
    private ITurnierManagement management;

    @Inject
    private ISkaterManagement skaterManagement;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response getAllTurniere(){
        Collection<Turnier> turniere= this.management.getAll();
        if(turniere.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(management.getAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response getTurnier(@PathParam("id") int id)
    {
        Turnier turnier= this.management.get(id);
        if(turnier == null){
            return Response.noContent().build();
        }
        return Response.ok(management.get(id)).build();
    }
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response addTurnier(@FormParam("name") String name)
    {
        Turnier turnier = this.management.add(new TurnierDTO(name));
        if(turnier == null){
            return getAllTurniere();
        }
        return getAllTurniere();
    }
    @PUT
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response editTurnier(@PathParam("id") int id, @FormParam("name") String name, @FormParam("ort") String ort, @FormParam("datum") String date)
    {
        Turnier turnier = this.management.edit(id,new TurnierDTO(name,ort,date));
        if(turnier == null){
            return Response.noContent().build();
        }
        return Response.ok(turnier).build();
    }

    @DELETE
    @Path("{turnierId}")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response deleteSkater(@PathParam("turnierId") int turnierId, @FormParam("skaterId") int skaterId){
        Turnier turnier = this.management.get(turnierId);
        if (turnier == null) {
            return Response.noContent().build();
        }
        Skater skater = this.skaterManagement.get(skaterId);

        if (skater == null) {
            return Response.noContent().build();
        }
        boolean deleted = this.management.deleteSkater(turnierId, skater);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("{id}/skater")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response getAllSkaters(@PathParam("id") int id){
        Turnier turnier = this.management.get(id);
        if (turnier == null) {
            return Response.noContent().build();
        }

        Collection<Skater> skaters = turnier.getSkaters();
        if (skaters.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(skaters).build();
    }

    @POST
    @Path("{id}/skater")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response addSkater(@PathParam("id") int id, SkaterDTO skaterDto) {
        Turnier turnier = this.management.get(id);
        if (turnier == null) {
            return Response.noContent().build();
        }

        Skater skater = new Skater(skaterDto.getVorname(), skaterDto.getNachname(), skaterDto.getDisziplin(), skaterDto.getAlter());
        turnier.addSkater(skater);

        return Response.ok(turnier).build();
    }




}
