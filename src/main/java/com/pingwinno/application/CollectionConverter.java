package com.pingwinno.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class CollectionConverter {
    public static String convert(FindIterable<Document> source) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(source);
    }


}
