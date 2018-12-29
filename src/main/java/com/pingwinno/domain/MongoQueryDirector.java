package com.pingwinno.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pingwinno.infrastructure.QueryModel;

public class MongoQueryDirector {
    public static String makeBuilder(QueryModel queryModel) throws JsonProcessingException {
        MongoQueryBuilder mongoQueryBuilder = new MongoQueryBuilder(queryModel);
        if (queryModel.getEqualOperator()!= null){
            mongoQueryBuilder.setEquality();
        }
        if (queryModel.getSortBy() != null){
            mongoQueryBuilder.setSort();
        }
        if (!queryModel.getIncludeFields().isEmpty()){
            mongoQueryBuilder.setProjection();
        }
        return mongoQueryBuilder.buildJson();
    }
}
