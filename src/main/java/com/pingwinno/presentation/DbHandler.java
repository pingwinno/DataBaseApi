package com.pingwinno.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pingwinno.domain.MongoQueryDirector;
import com.pingwinno.infrastructure.QueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/db/streams/")
public class DbHandler {
    private static final Logger log = LoggerFactory.getLogger(DbHandler.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{streamer}")
    public Response getStreams(
            @PathParam("streamer") String streamer,
            @DefaultValue("desc") @QueryParam("sortingOrder") String sortingOrder,
            @DefaultValue("date") @QueryParam("sortBy") String sortBy,
            @QueryParam("equalOperator") String equalOperator,
            @QueryParam("equalsField") String equalsField,
            @QueryParam("equalsValue") String equalsValue,
            @DefaultValue("20") @QueryParam("limit") int limit,
            @DefaultValue("0") @QueryParam("skip") int skip,
            @QueryParam("includeFields") List<String> includeFields
    ) throws IOException {
        log.info("incoming request");
        QueryModel queryModel = new QueryModel();
        queryModel.setCollection(streamer);
        queryModel.setSortingOrder(sortingOrder);
        queryModel.setSortBy(sortBy);

        if (equalOperator != null) {
            queryModel.setEqualOperator(equalOperator);
            queryModel.setEqualsField(equalsField);
            queryModel.setEqualsValue(equalsValue);
        }

        queryModel.setLimit(limit);
        queryModel.setSkip(skip);


        if (includeFields.isEmpty()) {
            includeFields.add("_id");
            includeFields.add("date");
            includeFields.add("title");
            includeFields.add("game");
            includeFields.add("duration");
            includeFields.add("animated_preview");
        }
        queryModel.setIncludeFields(new ArrayList<>(includeFields));
        log.trace("Request: {}", queryModel.toString());
        return Response.accepted().entity(MongoQueryDirector.makeBuilder(queryModel))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST")
                .header("Access-Control-Max-Age", "1209600").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{streamer}/{_id}")
    public Response getStream(
            @PathParam("streamer") String streamer,
            @QueryParam("sortingOrder") String sortingOrder,
            @QueryParam("sortBy") String sortBy,
            @DefaultValue("eq") @QueryParam("equalOperator") String equalOperator,
            @DefaultValue("_id") @QueryParam("equalsField") String equalsField,
            @PathParam("_id") String equalsValue,
            @DefaultValue("20") @QueryParam("limit") int limit,
            @DefaultValue("0") @QueryParam("skip") int skip,
            @DefaultValue("timeline_preview") @QueryParam("includeFields") List<String> includeFields
    ) throws JsonProcessingException {
        log.info("incoming request");
        QueryModel queryModel = new QueryModel();
        queryModel.setCollection(streamer);
        queryModel.setSortingOrder(sortingOrder);
        queryModel.setSortBy(sortBy);

        queryModel.setEqualOperator(equalOperator);
        queryModel.setEqualsField(equalsField);
        queryModel.setEqualsValue(equalsValue);

        queryModel.setLimit(limit);
        queryModel.setSkip(skip);

        queryModel.setIncludeFields(new ArrayList<>(includeFields));
        log.trace("Request: {}", queryModel.toString());
        return Response.accepted().entity(MongoQueryDirector.makeBuilder(queryModel))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST")
                .header("Access-Control-Max-Age", "1209600").build();
    }
}
