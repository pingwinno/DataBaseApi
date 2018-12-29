package com.pingwinno.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.management.Query;
import java.util.List;

public interface QueryBuilder {
    void setSort();
    void setEquality();
    void setProjection();
    String buildJson() throws JsonProcessingException;

}
