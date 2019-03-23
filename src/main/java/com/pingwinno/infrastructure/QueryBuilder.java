package com.pingwinno.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface QueryBuilder {
    void setSort();

    void setEquality();

    void setProjection();

    String buildJson() throws JsonProcessingException;

}
