package com.pingwinno.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.pingwinno.infrastructure.MongoDBHandler;
import com.pingwinno.infrastructure.QueryBuilder;
import com.pingwinno.infrastructure.QueryModel;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoQueryBuilder implements QueryBuilder {


    QueryModel queryModel;
    private Bson sort = new Document();
    private Bson filters = new Document();
    private Bson projection = new Document();

    public MongoQueryBuilder(QueryModel queryModel) {
        this.queryModel = queryModel;
    }


    @Override
    public void setSort() {
        switch (queryModel.getSortingOrder()) {
            case "asc":
                sort = Sorts.ascending(queryModel.getSortBy());
                break;
            case "desc":
                sort = Sorts.descending(queryModel.getSortBy());
                break;
        }

    }

    @Override
    public void setEquality() {
        switch (queryModel.getEqualOperator()) {
            case "eq":
                filters = Filters.eq(queryModel.getEqualsField(), queryModel.getEqualsValue());
                break;
            case "gt":
                filters = Filters.gt(queryModel.getEqualsField(), queryModel.getEqualsValue());
                break;
            case "ls":
                filters = Filters.lt(queryModel.getEqualsField(), queryModel.getEqualsValue());
                break;
        }
    }

    @Override
    public void setProjection() {
        projection = Projections.include(queryModel.getIncludeFields());
    }

    @Override
    public String buildJson() throws JsonProcessingException {
        FindIterable<Document> queryResult = MongoDBHandler.getCollection(queryModel.getCollection()).find(filters).
                projection(projection).sort(sort).skip(queryModel.getSkip()).limit(queryModel.getLimit());
        return new ObjectMapper().writeValueAsString(queryModel);
    }
}
