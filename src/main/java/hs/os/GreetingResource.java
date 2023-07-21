package hs.os;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance hello();

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance hello() {

        return Templates.hello();
    }
    /*
    @GET
    @Path("/hello/base")
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance base() {

        return GreetingResource.Templates.base();
    }*/
}