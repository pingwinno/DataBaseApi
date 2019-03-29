package com.pingwinno.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    private static String SITES_PATH = "/usr/local/StreamArchiveBackend/";
    // private static String SITES_PATH = "/home/shiro/";
    @Context
    UriInfo uri;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getIndex() throws IOException {
        String streamer;
        String baseUrl = uri.getBaseUri().getHost();
        String[] urlParts = baseUrl.split("\\.");
        log.trace(baseUrl);

        if (!urlParts[0].equals("streamarchive")) {
            streamer = urlParts[0];
        } else {
            streamer = "hub";
        }
        java.nio.file.Path path = Paths.get(SITES_PATH + streamer + "/index.html");
        log.debug("streamer {} page loaded", streamer);
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
    }

    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public Response getAbout() throws IOException {
        String streamer;
        String baseUrl = uri.getBaseUri().getHost();

        String[] urlParts = baseUrl.split("\\.");
        log.trace(baseUrl);

        if (!urlParts[0].equals("streamarchive")) {
            streamer = urlParts[0];
        } else {
            streamer = "hub";
        }
        java.nio.file.Path path = Paths.get(SITES_PATH + streamer + "/index.html");
        log.debug("streamer {} page loaded", streamer);
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
    }

    @GET
    @Path("/video/{uuid}")
    @Produces(MediaType.TEXT_HTML)
    public Response getVideo() throws IOException {

        String baseUrl = uri.getBaseUri().getHost();

        String[] urlParts = baseUrl.split("\\.");
        log.trace(baseUrl);
        String streamer = urlParts[0];
        java.nio.file.Path path = Paths.get(SITES_PATH + streamer + "/index.html");
        log.debug("streamer {} page loaded", streamer);
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
    }

    @GET
    @Path("{any: .*}")
    public Response downloadFile() throws IOException {
        String streamer;
        String baseUrl = uri.getBaseUri().getHost();
        String[] urlParts = baseUrl.split("\\.");
        if (!urlParts[0].equals("streamarchive")) {
            streamer = urlParts[0];
        } else {
            streamer = "hub";
        }
        log.info(uri.getAbsolutePath() + " " + SITES_PATH + streamer + "/" + uri.getPath());
        java.nio.file.Path path = Paths.get(SITES_PATH + streamer + "/" + uri.getPath());
            return Response.ok().entity(new File(path.toString())).type(Files.probeContentType(path))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST")
                    .header("Access-Control-Max-Age", "1209600").build();
    }

}



