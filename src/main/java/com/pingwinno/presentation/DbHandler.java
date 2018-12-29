package com.pingwinno.presentation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingwinno.domain.MongoQueryDirector;
import com.pingwinno.infrastructure.QueryModel;
import com.pingwinno.infrastructure.RestExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/db/streams")
public class DbHandler {
    private static final Logger log = LoggerFactory.getLogger(DbHandler.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response dbQuery(@QueryParam("query") String query) throws IOException {
        log.info("incoming request");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return Response.accepted().entity(MongoQueryDirector.makeBuilder(mapper.readValue(query, QueryModel.class))).build();
    }
}
