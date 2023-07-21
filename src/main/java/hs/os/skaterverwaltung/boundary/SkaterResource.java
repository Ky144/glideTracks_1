package hs.os.skaterverwaltung.boundary;

import com.oracle.svm.core.annotate.Inject;
import hs.os.skaterverwaltung.control.ISkaterManagement;
import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/skater")
@ApplicationScoped
public class SkaterResource {

    @Inject
    private ISkaterManagement management;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response getAllSkater(){
        Collection<Skater> skater= this.management.getAll();
        if(skater.isEmpty()){
            return Response.noContent().build();
        }
        //return Response.ok(management.getAll()).build();
         return Response.ok(skater).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response getSkater(@PathParam("id") int id)
    {
        Skater skater= this.management.get(id);
        if(skater == null){
            return Response.noContent().build();
        }
        return Response.ok(management.get(id)).build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response addSkater(@FormParam("vorname") String vorname, @FormParam("nachname") String nachname,  @FormParam("disziplin") String disziplin, @FormParam("alter") int alter)
    {
        Skater skater = this.management.add(new SkaterDTO(vorname,nachname,disziplin,alter));
        if(skater == null){
            //return getAllSkater();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        //return getAllSkater();
        return Response.status(Response.Status.CREATED).entity(skater).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response editSkater(@PathParam("id") int id, @FormParam("vorname") String vorname, @FormParam("nachname") String nachname, @FormParam("disziplin") String disziplin, @FormParam("alter") int alter)
    {
        Skater skater = this.management.edit(id,new SkaterDTO(vorname,nachname,disziplin,alter));
        if(skater == null){
            //return Response.noContent().build();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(skater).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response deleteSkater(@PathParam("id") int id){
        boolean deleted = this.management.delete(id);
        if(deleted){
            return Response.ok().build();
        }
        //return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }






}
