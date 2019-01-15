package com.pingwinno.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;


@Path("/")
public class SiteLoader {
    private static final Logger log = LoggerFactory.getLogger(SiteLoader.class);
    @Context
    UriInfo uri;
    private ClassLoader classLoader = getClass().getClassLoader();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getIndex() {

        String baseUrl = uri.getBaseUri().toString();
        String[] urlParts = baseUrl.split(".");

        if (urlParts.length == 3) {
            String user = urlParts[0];
            File file = new File(classLoader.getResource(user + "/index.html").getFile());
            return Response.ok().build();
        } else {
            File file = new File(classLoader.getResource("hub/index.html").getFile());

            return Response.ok().entity(file).build();
        }
    }

    @GET
    @Path("static/{folder}/{file}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("folder") String folder, @PathParam("file") String fileName) {

        String baseUrl = uri.getBaseUri().toString();
        String[] urlParts = baseUrl.split(".");

        if (urlParts.length == 3) {
            String user = urlParts[0];
            File file = new File(classLoader.getResource(user + "/static/" + folder + "/" + fileName).getFile());

            return Response.ok().entity(file).build();
        } else {
            File file = new File(classLoader.getResource("hub/" + fileName).getFile());

            return Response.ok().entity(file).build();
        }

    }

    @GET
    @Path("{file}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("file") String fileName) {

        String baseUrl = uri.getBaseUri().toString();
        String[] urlParts = baseUrl.split(".");

        File file = new File(classLoader.getResource("hub/" + fileName).getFile());

        return Response.ok().entity(file).build();
    }

}



