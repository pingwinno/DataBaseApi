package com.pingwinno.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.FindIterable;
import com.pingwinno.application.CollectionConverter;
import com.pingwinno.infrastructure.MongoDBHandler;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/get_all_streams")
public class OldApi {
    private static final Logger log = LoggerFactory.getLogger(OldApi.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws JsonProcessingException {
        FindIterable<Document> collection = MongoDBHandler.getCollection("olyashaa").find().projection(new Document("title", 1)
                .append("date", 1)
                .append("game", 1));

        return Response.accepted().entity(CollectionConverter.convert(collection).replace("_id", "uuid")).build();
    }
}
