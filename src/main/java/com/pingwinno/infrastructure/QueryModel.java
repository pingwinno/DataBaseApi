package com.pingwinno.infrastructure;

import java.util.ArrayList;

public class QueryModel {
    private String collection; // name of collection
    private String sortingOrder; // can be "asc" and "desc"
    private String sortBy; // name of field for sorting
    private String equalOperator; // have options "eq" equals, "gt" greater than and "lt" less than
    private String equalsField;
    private String equalsValue;
    private int limit; // limit for elements
    private int skip; // skip elements. Positive number skip begin of list, negative from end.
    private ArrayList<String> includeFields;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getEqualOperator() {
        return equalOperator;
    }

    public void setEqualOperator(String equalOperator) {
        this.equalOperator = equalOperator;
    }

    public String getEqualsField() {
        return equalsField;
    }

    public void setEqualsField(String equalsField) {
        this.equalsField = equalsField;
    }

    public String getEqualsValue() {
        return equalsValue;
    }

    public void setEqualsValue(String equalsValue) {
        this.equalsValue = equalsValue;
    }

    public ArrayList<String> getIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(ArrayList<String> includeFields) {
        this.includeFields = includeFields;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}
