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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Path("/")
public class SiteLoader {
    private static final Logger log = LoggerFactory.getLogger(SiteLoader.class);
    @Context
    UriInfo uri;


    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getIndex() throws IOException {

        String baseUrl = uri.getBaseUri().getHost();
        String[] urlParts = baseUrl.split("\\.");
        log.trace(baseUrl);


        if (!urlParts[0].equals("streamarchive")) {
            String user = urlParts[0];
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/"+user + "/index.html");
            log.debug("streamer {} page loaded", user);
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        } else {
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/hub/index.html");

            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        }
    }

    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public Response getAbout() throws IOException {

        String baseUrl = uri.getBaseUri().getHost();
        String[] urlParts = baseUrl.split("\\.");
        log.trace(baseUrl);


        if (!urlParts[0].equals("streamarchive")) {
            String user = urlParts[0];
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/" + user + "/index.html");
            log.debug("streamer {} page loaded", user);
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        } else {
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/hub/index.html");

            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        }
    }

    @GET
    @Path("/video/{uuid}")
    @Produces(MediaType.TEXT_HTML)
    public Response getVideo() throws IOException {

        String baseUrl = uri.getBaseUri().getHost();
        String[] urlParts = baseUrl.split("\\.");
        log.trace(baseUrl);


        if (!urlParts[0].equals("streamarchive")) {
            String user = urlParts[0];
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/" + user + "/index.html");
            log.debug("streamer {} page loaded", user);
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        } else {
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/hub/index.html");

            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        }
    }

    @GET
    @Path("static/{folder}/{file}")
    public Response downloadFile(@PathParam("folder") String folder, @PathParam("file") String fileName) throws IOException {

        String baseUrl = uri.getBaseUri().getHost();
        String[] urlParts = baseUrl.split("\\.");
        log.debug("folder: {}, file: {}", folder, fileName);
        if (!urlParts[0].equals("streamarchive")) {
            String user = urlParts[0];
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/"+ user + "/static/" + folder + "/" + fileName);

            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        } else {
            java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/hub/" + fileName);

            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
        }

    }

    @GET
    @Path("{file}")
    public Response downloadFile(@PathParam("file") String fileName) throws IOException {


        java.nio.file.Path path = Paths.get("/usr/local/StreamArchiveBackend/hub/" + fileName);

        return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST")
                .header("Access-Control-Max-Age", "1209600").build();
    }


}



